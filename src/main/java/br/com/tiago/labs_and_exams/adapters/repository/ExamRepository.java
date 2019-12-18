package br.com.tiago.labs_and_exams.adapters.repository;

import br.com.tiago.labs_and_exams.entities.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {

    ExamEntity findFirstByName(String name);
    List<ExamEntity> findDistinctByStatus(String status);
    List<ExamEntity> findAllByStatusAndLaboratoriesId(String status, long id);
    boolean existsByStatusAndLaboratoriesId(String status, long id);
    boolean existsByName(String name);
}
