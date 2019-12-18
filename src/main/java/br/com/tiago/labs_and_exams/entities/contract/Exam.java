package br.com.tiago.labs_and_exams.entities.contract;

import br.com.tiago.labs_and_exams.entities.enums.StatusEnum;
import br.com.tiago.labs_and_exams.entities.enums.ExamTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Exam {

    private Long id;
    private String name;
    private ExamTypeEnum type;
    private StatusEnum status;
}
