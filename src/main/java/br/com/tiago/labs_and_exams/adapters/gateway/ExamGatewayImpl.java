package br.com.tiago.labs_and_exams.adapters.gateway;

import br.com.tiago.labs_and_exams.adapters.gateway.interfaces.ExamGateway;
import br.com.tiago.labs_and_exams.adapters.repository.ExamRepository;
import br.com.tiago.labs_and_exams.entities.contract.Exam;
import br.com.tiago.labs_and_exams.entities.converter.interfaces.ExamConverter;
import br.com.tiago.labs_and_exams.entities.entity.ExamEntity;
import br.com.tiago.labs_and_exams.entities.enums.StatusEnum;
import br.com.tiago.labs_and_exams.frameworkAndDrivers.exception.GlobalExceptionHandlers;
import br.com.tiago.labs_and_exams.frameworkAndDrivers.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ExamGatewayImpl implements ExamGateway {

    @Autowired
    ExamConverter converter;

    @Autowired
    ExamRepository repository;


    @Override
    public boolean isExistById(Long id) {

        return repository.existsById(id);
    }

    @Override
    public boolean isExistByName(String name) {

        return repository.existsByName(name);
    }

    @Override
    public Exam getById(Long id) {

        ExamEntity entity = repository.findById(id).get();
        return converter.convertToContract(entity);
    }

    @Override
    public Exam getByName(String name) {

        ExamEntity entity = repository.findFirstByName(name);
        return converter.convertToContract(entity);
    }

    @Override
    public List<Exam> getAllByStatus(StatusEnum status) {

        List<ExamEntity> entityList = repository.findDistinctByStatus(status.getName());
        return converter.convertToContracts(entityList);
     }

    @Override
    public List<Exam> getAll() {

        List<ExamEntity> entityList = repository.findAll();
        return converter.convertToContracts(entityList);
    }

    @Override
    public List<Exam> getExamByStatusAndIdLaboratory(StatusEnum status, Long id) {

        List<ExamEntity> entityList = repository.findAllByStatusAndLaboratoriesId(status.getName(), id);
        return converter.convertToContracts(entityList);
    }

    @Override
    public boolean isExistsExamByStatusAndIdLaboratory(StatusEnum status, Long id) {

        return repository.existsByStatusAndLaboratoriesId(status.getName(), id);
    }

    @Override
    public Exam insert(Exam contract) {

        ExamEntity entity = converter.convertToPersistEntity(contract);
        entity = repository.save(entity);
        return converter.convertToContract(entity);
    }

    @Override
    public Exam update(Exam contract) {

        ExamEntity entity = converter.convertToUpdateEntity(contract);
        entity = repository.save(entity);
        return converter.convertToContract(entity);
    }

    @Override
    public void logicalDelete(Exam contract) {

        ExamEntity entity = converter.convertToDeleteEntity(contract);
        repository.save(entity);
    }
}
