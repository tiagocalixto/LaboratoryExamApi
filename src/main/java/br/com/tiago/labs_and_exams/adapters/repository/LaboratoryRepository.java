package br.com.tiago.labs_and_exams.adapters.repository;

import br.com.tiago.labs_and_exams.entities.contract.Exam;
import br.com.tiago.labs_and_exams.entities.entity.LaboratoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratoryRepository extends JpaRepository<LaboratoryEntity, Long> {

    LaboratoryEntity findFirstByName(String name);
    List<LaboratoryEntity> findDistinctByStatus(String status);
    List<LaboratoryEntity> findDistinctByStatusAndExamsId(String status, Long ExamId);
    List<LaboratoryEntity> findDistinctByExamsId(Long examId);
    boolean existsByName(String name);
}
