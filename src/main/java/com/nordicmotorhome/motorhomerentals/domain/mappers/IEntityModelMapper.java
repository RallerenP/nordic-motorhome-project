package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.domain.entities.BaseEntity;

import java.util.List;

public interface IEntityModelMapper<E extends BaseEntity, M> {
    M mapToModel(E entity);

    List<M> mapAllToModel(List<E> entities);
}
