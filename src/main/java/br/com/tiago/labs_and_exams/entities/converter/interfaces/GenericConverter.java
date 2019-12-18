package br.com.tiago.labs_and_exams.entities.converter.interfaces;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

interface GenericConverter<T,U> {

    T convertToPersistEntity(final U contract);
    T convertToUpdateEntity(final U contract);
    T convertToDeleteEntity(final U contract);
    U convertToContract(final T entity);

    default List<T> convertToPersistedEntities(final Collection<U> contracts) {
        return contracts.stream().map(this::convertToPersistEntity).collect(Collectors.toList());
    }

    default List<T> convertToUpdatedEntities(final Collection<U> contracts) {
        return contracts.stream().map(this::convertToUpdateEntity).collect(Collectors.toList());
    }

    default List<T> convertToDeletedEntities(final Collection<U> contracts) {
        return contracts.stream().map(this::convertToDeleteEntity).collect(Collectors.toList());
    }

    default List<U> convertToContracts(final Collection<T> entities) {
        return entities.stream().map(this::convertToContract).collect(Collectors.toList());
    }
}
