package br.com.tiago.labs_and_exams.useCase.interfaces;

import br.com.tiago.labs_and_exams.entities.contract.Laboratory;

import java.util.List;

public interface LaboratoryUseCase extends GenericUseCase<Laboratory> {

    List<Laboratory> getActiveLaboratoryByActiveExamName(String examName);
    List<Laboratory> getAllLaboratoryByExamName (String examName);
}
