package br.com.tiago.labs_and_exams.entities.converter;

import br.com.tiago.labs_and_exams.entities.contract.Exam;
import br.com.tiago.labs_and_exams.entities.converter.interfaces.ExamConverter;
import br.com.tiago.labs_and_exams.entities.entity.ExamEntity;
import br.com.tiago.labs_and_exams.entities.enums.ExamTypeEnum;
import br.com.tiago.labs_and_exams.entities.enums.StatusEnum;
import org.springframework.stereotype.Component;

@Component
public class ExamConverterImpl implements ExamConverter {

    @Override
    public ExamEntity convertToPersistEntity(Exam contract) {

        if (contract == null) {
            return null;
        }

        ExamEntity entity = convertToEntity(contract);
        entity.beforePersist();

        return entity;
    }

    @Override
    public ExamEntity convertToUpdateEntity(Exam contract) {

        if (contract == null) {
            return null;
        }

        ExamEntity entity = convertToEntity(contract);
        entity.beforeUpdate();

        return entity;
    }

    @Override
    public ExamEntity convertToDeleteEntity(Exam contract) {

        if (contract == null) {
            return null;
        }

        ExamEntity entity = convertToEntity(contract);
        entity.beforeRemove();
        entity.setSoftDeletedColumnAsTrue();

        return entity;
    }

    private ExamEntity convertToEntity(Exam contract){

        ExamEntity entity = new ExamEntity();

        entity.setId(contract.getId());
        entity.setName(contract.getName());
        entity.setType(contract.getType().getName());
        entity.setStatus(contract.getStatus().getName());

        return entity;
    }

    @Override
    public Exam convertToContract(ExamEntity entity) {

        if (entity == null) {
            return null;
        }

        Exam contract = new Exam();

        contract.setId(entity.getId());
        contract.setName(entity.getName());
        contract.setType(ExamTypeEnum.valueOf(entity.getType()));
        contract.setStatus(StatusEnum.valueOf(entity.getStatus()));

        return contract;
    }
}
