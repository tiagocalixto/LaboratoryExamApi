package br.com.tiago.labs_and_exams.entities.enums;

import lombok.Getter;
import lombok.ToString;

@ToString
public enum StatusEnum {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    @Getter
    private final String name;

    StatusEnum(String value) {
        this.name = value;
    }
}
