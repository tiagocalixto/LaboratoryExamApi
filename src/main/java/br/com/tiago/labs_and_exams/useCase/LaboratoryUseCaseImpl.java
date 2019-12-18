package br.com.tiago.labs_and_exams.useCase;

import br.com.tiago.labs_and_exams.adapters.gateway.interfaces.LaboratoryGateway;
import br.com.tiago.labs_and_exams.entities.contract.Exam;
import br.com.tiago.labs_and_exams.entities.contract.Laboratory;
import br.com.tiago.labs_and_exams.entities.enums.StatusEnum;
import br.com.tiago.labs_and_exams.frameworkAndDrivers.exception.DuplicateKeyException;
import br.com.tiago.labs_and_exams.frameworkAndDrivers.exception.ResourceBadRequest;
import br.com.tiago.labs_and_exams.frameworkAndDrivers.exception.ResourceNotFoundException;
import br.com.tiago.labs_and_exams.useCase.interfaces.ExamUseCase;
import br.com.tiago.labs_and_exams.useCase.interfaces.LaboratoryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaboratoryUseCaseImpl implements LaboratoryUseCase {

    @Autowired
    LaboratoryGateway gateway;

    @Autowired
    ExamUseCase examUseCase;

    @Override
    public List<Laboratory> getActiveLaboratoryByActiveExamName(String examName) {

        Exam exam = examUseCase.getByName(examName);

        if(exam.getStatus() != StatusEnum.ACTIVE){
            throw new ResourceBadRequest("The exam " + exam.getName() + " is inactive!");
        }

        List<Laboratory> laboratories = gateway.getLaboratoryByStatusAndExamId(StatusEnum.ACTIVE, exam.getId());

        for(int i = 0; i < laboratories.size(); i++){
            laboratories.get(i).setExams(examUseCase.getActiveExamsByLaboratoryId(laboratories.get(i).getId()));
        }

        return laboratories;
    }

    @Override
    public List<Laboratory> getAllLaboratoryByExamName(String examName) {

        Exam exam = examUseCase.getByName(examName);
        return gateway.getLaboratoryByExamId(exam.getId());
    }

    @Override
    public Laboratory getByName(String name) {

        Laboratory laboratory = gateway.getByName(name);
        laboratory.setExams(examUseCase.getActiveExamsByLaboratoryId(laboratory.getId()));
        return laboratory;
    }

    @Override
    public List<Laboratory> getAllActive() {

        List<Laboratory> laboratories = gateway.getAllByStatus(StatusEnum.ACTIVE);

        for(int i = 0; i < laboratories.size(); i++){
            laboratories.get(i).setExams(examUseCase.getActiveExamsByLaboratoryId(laboratories.get(i).getId()));
        }

        return laboratories;
    }

    @Override
    public List<Laboratory> getAll() {

        return gateway.getAll();
    }

    @Override
    public Laboratory insert(Laboratory contract) {

        contract.setStatus(StatusEnum.ACTIVE);

        if(gateway.isExistByName(contract.getName())){
            throw new DuplicateKeyException("Already exists a Laboratory whit the name " + contract.getName());
        }

        if(!isLaboratoryValidToInsert(contract)){
            throw new ResourceBadRequest("Incorrect Laboratory Data to Insert");
        }

        if(!examUseCase.isListOfExamsValid(contract.getExams())){
            throw new ResourceBadRequest("The Laboratory you trying to insert contains inactive/unregistered exams!");
        }

        return gateway.insert(contract);
    }

    @Override
    public List<Laboratory> insertInBlock(List<Laboratory> contracts) {

        List<Laboratory> laboratoriesToInsert = new ArrayList<>();

        for(Laboratory laboratory : contracts){

            laboratory.setStatus(StatusEnum.ACTIVE);

            if(itemOfBlockIsValidToInsert(laboratory)){
                laboratoriesToInsert.add(gateway.insert(laboratory));
            }
        }

        if(laboratoriesToInsert.size() == 0){
            throw new ResourceBadRequest("All the laboratories in the block are inconsistent");
        }

        return laboratoriesToInsert;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Laboratory update(Laboratory contract, Long id) {

        contract.setId(id);

        if(!gateway.isExistById(contract.getId())){
            throw new ResourceNotFoundException("Laboratory not found with id " + contract.getId());
        }

        if(!isLaboratoryValidToUpdate(contract)){
            throw new ResourceBadRequest("Incorrect Laboratory Data to Update");
        }

        if(isTryingToUseARegisteredLaboratoryName(contract)){
            throw new ResourceBadRequest("Already exists a laboratory with the name " + contract.getName());
        }

        if(!examUseCase.isListOfExamsValid(contract.getExams())){
            throw new ResourceBadRequest("The Laboratory you trying to update contains inactive/unregistered exams!");
        }

        return gateway.update(contract);
    }

    @Override
    public List<Laboratory> updateInBlock(List<Laboratory> contracts) {

        List<Laboratory> laboratoriesToUpdate = new ArrayList<>();

        for(Laboratory laboratory : contracts){

            if(itemOfBlockIsValidToUpdate(laboratory)){
                laboratoriesToUpdate.add(gateway.update(laboratory));
            }
        }

        if(laboratoriesToUpdate.size() == 0){
            throw new ResourceBadRequest("All the laboratories in the block are inconsistent");
        }

        return laboratoriesToUpdate;
    }

    @Override
    public void delete(Long id) {

        if(!gateway.isExistById(id)){
            throw new ResourceNotFoundException("Laboratory not found for delete");
        }

        gateway.logicalDelete(gateway.getById(id));
    }

    @Override
    public List<Laboratory> deleteInBlock(List<Long> ids) {

        List<Laboratory> laboratories = new ArrayList<>();

        for(Long id : ids){

            if(gateway.isExistById(id)){

                Laboratory laboratory = gateway.getById(id);
                gateway.logicalDelete(laboratory);
                laboratories.add(laboratory);
            }
        }

        return laboratories;
    }


    //private methods to help with data consistency - BEGIN

    private boolean itemOfBlockIsValidToInsert(Laboratory laboratory){

        if(gateway.isExistByName(laboratory.getName())){
            return false;
        }

        if(!isLaboratoryValidToInsert(laboratory)){
            return false;
        }

        return examUseCase.isListOfExamsValid(laboratory.getExams());
    }

    private boolean itemOfBlockIsValidToUpdate(Laboratory laboratory){

        if(!gateway.isExistById(laboratory.getId())){
            return false;
        }

        if(!isLaboratoryValidToUpdate(laboratory)){
            return false;
        }

        if(isTryingToUseARegisteredLaboratoryName(laboratory)){
            return false;
        }

        if(!examUseCase.isListOfExamsValid(laboratory.getExams())){
            return false;
        }

        return examUseCase.isListOfExamsValid(laboratory.getExams());
    }

    private boolean isLaboratoryValidToInsert(Laboratory laboratory){

        if(!isLaboratoryValid(laboratory)){
            return false;
        }

        return laboratory.getId() == 0 || laboratory.getId() == null;
    }

    private boolean isLaboratoryValidToUpdate(Laboratory laboratory){

        if(!isLaboratoryValid(laboratory)){
            return false;
        }

        return laboratory.getId() > 0;
    }

    private boolean isLaboratoryValid(Laboratory laboratory){

        if(laboratory.getName().equals("") || laboratory.getName() == null){
            return false;
        }

        return laboratory.getStatus() != null;
    }

    private boolean isTryingToUseARegisteredLaboratoryName(Laboratory laboratory){

        Laboratory laboratoryRecoveredFromGateway = gateway.getById(laboratory.getId());

        if(!laboratory.getName().equals(laboratoryRecoveredFromGateway.getName())){
            return gateway.isExistByName(laboratory.getName());
        }

        return false;
    }

    //private methods to help with data consistency - END

}
