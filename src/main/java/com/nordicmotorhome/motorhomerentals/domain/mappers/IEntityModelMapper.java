package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.domain.entities.BaseEntity;

import java.util.List;
// AUTHOR: RAP
// An interface used for each model mapper, is used for conversion between an entity to a model
public interface IEntityModelMapper<E extends BaseEntity, M> {
    M mapToModel(E entity);

    List<M> mapAllToModel(List<E> entities);
}
