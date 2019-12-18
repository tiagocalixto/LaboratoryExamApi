package br.com.tiago.labs_and_exams.adapters.gateway.interfaces;

import br.com.tiago.labs_and_exams.entities.enums.StatusEnum;
import java.util.List;


interface GenericGateway<T> {

    boolean isExistById(Long id);
    boolean isExistByName(String name);
    T getById(Long id);
    T getByName(String name);
    List<T> getAllByStatus(StatusEnum status);
    List<T> getAll();
    T insert(T contract);
    T update(T contract);
    void logicalDelete(T contract);
}

