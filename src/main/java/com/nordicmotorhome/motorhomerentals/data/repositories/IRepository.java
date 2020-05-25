package com.nordicmotorhome.motorhomerentals.data.repositories;

import com.nordicmotorhome.motorhomerentals.data.entity.BaseEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.util.List;

public interface IRepository<T extends BaseEntity> {

    /**
     * Gets an specific entity
     * @param id The id of the entity to get
     * @return The fetched entity
     * @throws NoSuchEntityException If no entities with the specified ID were found.
     */
    T getById(int id) throws NoSuchEntityException;

    /**
     * Gets all entities
     * @return Returns all entities
     * @throws NoSuchEntityException If no entites were found
     */
    List<T> getAll() throws NoSuchEntityException;

    /***
     * Creates an entity
     * @param entity The entity to create
     * @return Returns the newly created entity
     */
    T create(T entity);

    /***
     * Deletes an entity
     * @param entity The entity to delete
     */
    void delete(T entity);

    /**
     * Updates an entity
     * @param entity The entity to save
     * @return new entity with updated information
     */
    T save(T entity);

    /**
     * Finds a single entity
     * @param key The key to include in the condition
     * @param value The value to include
     * @return A single entity
     */
    T findOne(String key, String value) throws NoSuchEntityException;


    T findOne(String key, int value) throws NoSuchEntityException;
}