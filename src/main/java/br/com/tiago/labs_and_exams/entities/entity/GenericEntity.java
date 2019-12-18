package br.com.tiago.labs_and_exams.entities.entity;


import br.com.tiago.labs_and_exams.entities.enums.OperationAuditEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
@ToString
public class GenericEntity implements Serializable {

    @Transient
    private static final String prefix = "aud_";

    @Column(name = prefix+"created_on", updatable= false)
    private LocalDateTime createdOn;

    @Column(name = prefix+"created_by", updatable= false)
    private String createdBy;

    @Column(name = prefix+"updated_on")
    private LocalDateTime updatedOn;

    @Column(name = prefix+"updated_by")
    private String updatedBy;

    @Column(name = prefix+"operation")
    private String operation;

    @Column(name = "deleted")
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Integer deleted = 0;

    public void setSoftDeletedColumnAsTrue(){
        deleted = 1;
    }

    public void beforePersist() {
        createdOn = LocalDateTime.now();
        createdBy = "User Insert";
        operation = OperationAuditEnum.INSERTED.getName();
    }

    public void beforeUpdate() {
        updatedOn = LocalDateTime.now();
        updatedBy = "User Update";
        operation = OperationAuditEnum.UPDATED.getName();
    }

    public void beforeRemove(){
        updatedOn = LocalDateTime.now();
        updatedBy = "User Remove";
        operation = OperationAuditEnum.DELETED.getName();
    }
}
