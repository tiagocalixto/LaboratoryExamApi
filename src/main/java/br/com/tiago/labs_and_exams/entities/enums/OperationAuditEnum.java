package br.com.tiago.labs_and_exams.entities.enums;

import lombok.Getter;
import lombok.ToString;

@ToString
public enum OperationAuditEnum {

    INSERTED("INSERTED"),
    UPDATED("UPDATED"),
    DELETED("DELETED");

    @Getter
    private final String name;

    OperationAuditEnum(String value) {
        this.name = value;
    }
}
