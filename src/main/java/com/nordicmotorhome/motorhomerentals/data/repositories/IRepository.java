package com.nordicmotorhome.motorhomerentals.data.repositories;

import com.nordicmotorhome.motorhomerentals.domain.entities.BaseEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.util.List;
// AUTHOR: RAP
// Interface used for repositories, is polymorphic since it can be implemented on anything, for instance a MongoDB
public interface IRepository<T> {

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

    /**
     * Finds a single entity
     * @param key The key to include in the condition
     * @param value The value to include
     * @return A single entity
     */
    T findOne(String key, int value) throws NoSuchEntityException;

    /**
     * Finds all entities matching query
     * @param key The key to match against
     * @param value The value to search for
     * @return Returns list of entites matching query
     */
    List<T> findAll(String key, String value) throws NoSuchEntityException;

    /**
     * Finds all entities matching query
     * @param key The key to match against
     * @param value The value to search for
     * @return Returns list of entites matching query
     */
    List<T> findAll(String key, int value) throws NoSuchEntityException;
}
