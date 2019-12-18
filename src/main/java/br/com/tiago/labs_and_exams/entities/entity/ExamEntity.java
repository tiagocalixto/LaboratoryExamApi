package br.com.tiago.labs_and_exams.entities.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_exam")
@JsonTypeName("exam")
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property = "typeObject", defaultImpl= ExamEntity.class)
@ToString
@Getter
@Setter
//@SQLDelete(sql = "UPDATE ExamEntity SET deleted = 1 WHERE id = ?")
@Where(clause = "deleted != 1")
public class ExamEntity extends GenericEntity {

    @Id
    @SequenceGenerator(name="tb_exam_id_auto", sequenceName="tb_exam_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tb_exam_id_auto")
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="status")
    private String status;

    @ManyToMany(mappedBy = "exams", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<LaboratoryEntity> laboratories;
}
