package br.com.tiago.labs_and_exams.adapters.gateway.interfaces;

import br.com.tiago.labs_and_exams.entities.contract.Exam;
import br.com.tiago.labs_and_exams.entities.contract.Laboratory;
import br.com.tiago.labs_and_exams.entities.enums.StatusEnum;

import java.util.List;


public interface LaboratoryGateway extends GenericGateway<Laboratory> {

    List<Laboratory> getLaboratoryByStatusAndExamId(StatusEnum status, Long examId);
    List<Laboratory> getLaboratoryByExamId(Long examId);
}
