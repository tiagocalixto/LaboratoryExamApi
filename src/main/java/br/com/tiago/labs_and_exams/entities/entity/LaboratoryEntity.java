package br.com.tiago.labs_and_exams.entities.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_laboratory")
@JsonTypeName("laboratory")
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property = "typeObject", defaultImpl= LaboratoryEntity.class)
@ToString
@Getter
@Setter
//@SQLDelete(sql = "UPDATE tb_laboratory SET deleted = 1 WHERE id = ?")
@Where(clause = "deleted != 1")
public class LaboratoryEntity extends GenericEntity {

    @Id
    @SequenceGenerator(name="tb_laboratory_id_auto", sequenceName="tb_laboratory_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tb_laboratory_id_auto")
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @ManyToMany(targetEntity = ExamEntity.class, fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "tb_laboratory_exam",
            joinColumns = {@JoinColumn(name = "id_lab")},
            inverseJoinColumns = {@JoinColumn(name = "id_exam")})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<ExamEntity> exams;

    @Column(name="status")
    private String status;
}
