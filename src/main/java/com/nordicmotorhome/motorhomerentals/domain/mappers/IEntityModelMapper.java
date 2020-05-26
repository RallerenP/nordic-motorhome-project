package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.data.entity.BaseEntity;

public interface IEntityModelMapper<E extends BaseEntity, M> {
    M mapToModel(E entity);
}
