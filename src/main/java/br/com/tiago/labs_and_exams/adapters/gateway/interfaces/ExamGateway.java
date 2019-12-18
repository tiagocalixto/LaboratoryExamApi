package br.com.tiago.labs_and_exams.adapters.gateway.interfaces;

import br.com.tiago.labs_and_exams.entities.contract.Exam;
import br.com.tiago.labs_and_exams.entities.enums.StatusEnum;

import java.util.List;


public interface ExamGateway extends GenericGateway<Exam> {

    List<Exam> getExamByStatusAndIdLaboratory(StatusEnum status, Long id);
    boolean isExistsExamByStatusAndIdLaboratory(StatusEnum status, Long id);
}
