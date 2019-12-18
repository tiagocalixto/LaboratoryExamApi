package br.com.tiago.labs_and_exams.adapters.gateway;

import br.com.tiago.labs_and_exams.adapters.gateway.interfaces.LaboratoryGateway;
import br.com.tiago.labs_and_exams.adapters.repository.LaboratoryRepository;
import br.com.tiago.labs_and_exams.entities.contract.Exam;
import br.com.tiago.labs_and_exams.entities.contract.Laboratory;
import br.com.tiago.labs_and_exams.entities.converter.interfaces.LaboratoryConverter;
import br.com.tiago.labs_and_exams.entities.entity.LaboratoryEntity;
import br.com.tiago.labs_and_exams.entities.enums.StatusEnum;
import br.com.tiago.labs_and_exams.frameworkAndDrivers.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LaboratoryGatewayImpl implements LaboratoryGateway {

    @Autowired
    LaboratoryConverter converter;

    @Autowired
    LaboratoryRepository repository;


    @Override
    public boolean isExistById(Long id) {

        return repository.existsById(id);
    }

    @Override
    public boolean isExistByName(String name) {

        return repository.existsByName(name);
    }

    @Override
    public Laboratory getById(Long id) {

        LaboratoryEntity entity = repository.findById(id).get();
        return converter.convertToContract(entity);
    }

    @Override
    public Laboratory getByName(String name) {

        LaboratoryEntity entity = repository.findFirstByName(name);
        return converter.convertToContract(entity);
    }

    @Override
    public List<Laboratory> getAllByStatus(StatusEnum status) {

        List<LaboratoryEntity> listEntity = repository.findDistinctByStatus(status.getName());
        return converter.convertToContracts(listEntity);
    }

    @Override
    public List<Laboratory> getAll() {

        List<LaboratoryEntity> listEntity = repository.findAll();
        return converter.convertToContracts(listEntity);
    }

    @Override
    public List<Laboratory> getLaboratoryByStatusAndExamId(StatusEnum status, Long examId) {

        List<LaboratoryEntity> listEntity = repository.findDistinctByStatusAndExamsId(status.getName(), examId);
        return converter.convertToContracts(listEntity);
    }

    @Override
    public List<Laboratory> getLaboratoryByExamId(Long examId) {

        List<LaboratoryEntity> listEntity = repository.findDistinctByExamsId(examId);
        return converter.convertToContracts(listEntity);
    }

    @Override
    public Laboratory insert(Laboratory contract) {

        LaboratoryEntity entity = converter.convertToPersistEntity(contract);
        entity = repository.saveAndFlush(entity);
        return converter.convertToContract(entity);
    }

    @Override
    public Laboratory update(Laboratory contract) {

        LaboratoryEntity entity = converter.convertToUpdateEntity(contract);
        entity = repository.saveAndFlush(entity);
        return converter.convertToContract(entity);
    }

    @Override
    public void logicalDelete(Laboratory contract) {

        LaboratoryEntity entity = converter.convertToDeleteEntity(contract);
        entity = repository.saveAndFlush(entity);
    }
}
