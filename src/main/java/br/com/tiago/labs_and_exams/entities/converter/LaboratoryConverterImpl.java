package br.com.tiago.labs_and_exams.entities.converter;

import br.com.tiago.labs_and_exams.entities.contract.Laboratory;
import br.com.tiago.labs_and_exams.entities.converter.interfaces.ExamConverter;
import br.com.tiago.labs_and_exams.entities.converter.interfaces.LaboratoryConverter;
import br.com.tiago.labs_and_exams.entities.entity.LaboratoryEntity;
import br.com.tiago.labs_and_exams.entities.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryConverterImpl implements LaboratoryConverter {

    @Autowired
    ExamConverter examConverter;


    @Override
    public LaboratoryEntity convertToPersistEntity(Laboratory contract) {

        if (contract == null) {
            return null;
        }

        LaboratoryEntity entity = convertToEntity(contract);
        entity.setExams(examConverter.convertToPersistedEntities(contract.getExams()));
        entity.beforePersist();
        return entity;
    }

    @Override
    public LaboratoryEntity convertToUpdateEntity(Laboratory contract) {

        if (contract == null) {
            return null;
        }

        LaboratoryEntity entity = convertToEntity(contract);
        entity.setExams(examConverter.convertToUpdatedEntities(contract.getExams()));
        entity.beforeUpdate();
        return entity;
    }

    @Override
    public LaboratoryEntity convertToDeleteEntity(Laboratory contract) {

        if (contract == null) {
            return null;
        }

        LaboratoryEntity entity = convertToEntity(contract);
        entity.setExams(examConverter.convertToDeletedEntities(contract.getExams()));
        entity.beforeRemove();
        entity.setSoftDeletedColumnAsTrue();
        return entity;
    }

    private LaboratoryEntity convertToEntity(Laboratory contract) {

        LaboratoryEntity entity = new LaboratoryEntity();

        entity.setId(contract.getId());
        entity.setAddress(contract.getAddress());
        entity.setName(contract.getName());
        entity.setStatus(contract.getStatus().getName());

        return entity;
    }

    @Override
    public Laboratory convertToContract(LaboratoryEntity entity) {

        if (entity == null) {
            return null;
        }

        Laboratory contract = new Laboratory();

        contract.setId(entity.getId());
        contract.setName(entity.getName());
        contract.setAddress(entity.getAddress());
        contract.setStatus(StatusEnum.valueOf(entity.getStatus()));
        contract.setExams(examConverter.convertToContracts(entity.getExams()));

        return contract;
    }
}