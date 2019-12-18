package br.com.tiago.labs_and_exams.useCase.interfaces;

import br.com.tiago.labs_and_exams.entities.contract.Exam;

import java.util.List;

public interface ExamUseCase extends GenericUseCase<Exam> {

    List<Exam> getActiveExamsByLaboratoryId (Long idLaboratory);
    boolean isListOfExamsValid(List<Exam> exams);
}
