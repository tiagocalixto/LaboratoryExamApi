package br.com.tiago.labs_and_exams.useCase.interfaces;

import br.com.tiago.labs_and_exams.entities.enums.StatusEnum;

import java.util.List;

interface GenericUseCase<T> {

    T getByName(String name);
    List<T> getAllActive();
    List<T> getAll();
    T insert(T contract);
    List<T> insertInBlock(List<T> contracts);
    T update(T contract, Long id);
    List<T> updateInBlock(List<T> contracts);
    void delete(Long id);
    List<T> deleteInBlock(List<Long> ids);
}
