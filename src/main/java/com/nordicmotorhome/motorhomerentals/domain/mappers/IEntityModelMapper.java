package com.nordicmotorhome.motorhomerentals.domain.mappers;

public interface IEntityModelMapper<E, M> {
    M mapToModel(E entity);
}
