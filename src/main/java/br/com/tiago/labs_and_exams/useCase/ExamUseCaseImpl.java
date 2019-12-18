package br.com.tiago.labs_and_exams.useCase;

import br.com.tiago.labs_and_exams.adapters.gateway.interfaces.ExamGateway;
import br.com.tiago.labs_and_exams.entities.contract.Exam;
import br.com.tiago.labs_and_exams.entities.enums.StatusEnum;
import br.com.tiago.labs_and_exams.frameworkAndDrivers.exception.DuplicateKeyException;
import br.com.tiago.labs_and_exams.frameworkAndDrivers.exception.ResourceBadRequest;
import br.com.tiago.labs_and_exams.frameworkAndDrivers.exception.ResourceNotFoundException;
import br.com.tiago.labs_and_exams.useCase.interfaces.ExamUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamUseCaseImpl implements ExamUseCase {

    @Autowired
    ExamGateway gateway;

    @Override
    public List<Exam> getActiveExamsByLaboratoryId(Long idLaboratory) {

        if(gateway.isExistsExamByStatusAndIdLaboratory(StatusEnum.ACTIVE, idLaboratory)) {
            return gateway.getExamByStatusAndIdLaboratory(StatusEnum.ACTIVE, idLaboratory);
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public boolean isListOfExamsValid(List<Exam> exams) {

        for (Exam exam : exams){
            if(!gateway.isExistById(exam.getId())){
                return false;
            }

            if(gateway.getById(exam.getId()).getStatus() != StatusEnum.ACTIVE){
                return false;
            }
        }

        return true;
    }

    @Override
    public Exam getByName(String name) {

        return gateway.getByName(name);
    }

    @Override
    public List<Exam> getAllActive() {

        return gateway.getAllByStatus(StatusEnum.ACTIVE);
    }

    @Override
    public List<Exam> getAll() {

        return gateway.getAll();
    }

    @Override
    public Exam insert(Exam contract) {

        contract.setStatus(StatusEnum.ACTIVE);

        if(gateway.isExistByName(contract.getName())){
            throw new DuplicateKeyException("Already exists a exam whit the name " + contract.getName());
        }

        if(!isExamValidToInsert(contract)){
            throw new ResourceBadRequest("Incorrect exam Data to Insert");
        }

        return gateway.insert(contract);
    }

    @Override
    public List<Exam> insertInBlock(List<Exam> contracts) {

        List<Exam> examsInserts = new ArrayList<Exam>();

        for(Exam exam:contracts){

            exam.setStatus(StatusEnum.ACTIVE);

            if(itemOfBlockIsValidToInsert(exam)){
                examsInserts.add(gateway.insert(exam));
            }
        }

        if(examsInserts.size() == 0){
            throw new ResourceBadRequest("All the exams in the block are inconsistent");
        }

        return examsInserts;
    }

    @Override
    public Exam update(Exam contract, Long id) {

        contract.setId(id);

        if(!gateway.isExistById(contract.getId())){
            throw new ResourceNotFoundException("Exam not found with id " + contract.getId());
        }

        if(!isExamValidToUpdate(contract)){
            throw new ResourceBadRequest("Incorrect exam Data to Update");
        }

        if(isTryingToUseARegisteredExamName(contract)){
            throw new ResourceBadRequest("Already exists a Exam with the name " + contract.getName());
        }

        return gateway.update(contract);
    }

    @Override
    public List<Exam> updateInBlock(List<Exam> contracts) {

        List<Exam> examsUpdated = new ArrayList<Exam>();

        for(Exam exam:contracts){

            if(itemOfBlockIsValidToUpdate(exam)){
                examsUpdated.add(gateway.update(exam));
            }
        }

        if(examsUpdated.size() == 0){
            throw new ResourceBadRequest("All the exams in block are inconsistent");
        }

        return examsUpdated;
    }

    @Override
    public void delete(Long id) {

        if(!gateway.isExistById(id)){
            throw new ResourceNotFoundException("Exam not found for delete");
        }

        gateway.logicalDelete(gateway.getById(id));
    }

    @Override
    public List<Exam> deleteInBlock(List<Long> ids) {

        List<Exam> exams = new ArrayList<>();

        for(Long id : ids){

            if(gateway.isExistById(id)) {

                Exam contract = gateway.getById(id);
                gateway.logicalDelete(contract);
                exams.add(contract);
            }
        }

        return exams;
    }


    //private methods to help with data consistency - BEGIN

    private boolean itemOfBlockIsValidToInsert(Exam exam){

        if(gateway.isExistByName(exam.getName())){
            return false;
        }

        return isExamValidToInsert(exam);
    }

    private boolean itemOfBlockIsValidToUpdate(Exam exam){
        if(!gateway.isExistById(exam.getId())){
            return false;
        }

        if(!isExamValidToUpdate(exam)){
            return false;
        }

        if(isTryingToUseARegisteredExamName(exam)){
            return false;
        }

        return true;
    }

    private boolean isExamValidToInsert(Exam exam){

        if(!isExamValid(exam)){
            return false;
        }
        return exam.getId() == 0 || exam.getId() == null;
    }

    private boolean isExamValid(Exam exam){

        if(exam.getName().equals("") || exam.getName() == null){
            return false;
        }

        if(exam.getStatus() == null){
            return false;
        }

        return exam.getType() != null;
    }

    private boolean isExamValidToUpdate(Exam exam){

        if(!isExamValid(exam)){
            return false;
        }

        return exam.getId() > 0;
    }

    private boolean isTryingToUseARegisteredExamName(Exam exam){

        Exam examRecoveredFromGateway = gateway.getById(exam.getId());

        if(!exam.getName().equals(examRecoveredFromGateway.getName())){
            return gateway.isExistByName(exam.getName());
        }

        return false;
    }

    //private methods to help with data consistency - END
}
