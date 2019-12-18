package br.com.tiago.labs_and_exams.entities.enums;

import lombok.Getter;
import lombok.ToString;

@ToString
public enum ExamTypeEnum {

    CLINICAL_ANALISYS("CLINICAL ANALYSIS"),
    IMAGE("IMAGE");

    @Getter
    private final String name;

    ExamTypeEnum(String value) {
        this.name = value;
    }
}
