package br.com.tiago.labs_and_exams.entities.contract;

import br.com.tiago.labs_and_exams.entities.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
public class Laboratory {

    private Long id;
    private String name;
    private String address;
    private StatusEnum status;
    private List<Exam> exams;
}
