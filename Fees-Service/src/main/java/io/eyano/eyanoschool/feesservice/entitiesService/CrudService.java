package io.eyano.eyanoschool.feesservice.entitiesService;

import io.eyano.eyanoschool.feesservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotNullException;

import java.util.List;
import java.util.Optional;
/**
 * Interface for the service layer that will be implemented by the service classes
 * @param <E> : Entity
 * @param <ID> : id of the entity
 * @version : 1.0
 * @since : 2021-04-18, Sun
 * @author : Pascal Tshingila
 * @see IdIsNullException
 * @see IdNotFoundException
 * @see IdNotNullException
 */
public interface CrudService<E,ID> {
    /**
     * Save an entity
     * @param entity : Entity to save
     * @return Entity saved
     */
    E save(E entity) throws IdNotFoundException, IdIsNullException;
    /**
     * Update an entity
     * @param entity : Entity to update
     * @return Entity updated
     * @throws IdNotFoundException : id not found
     */
    E update(E entity) throws IdNotFoundException, IdIsNullException;
    /**
     * Remove an entity
     * @param entity : Entity to remove
     * @return boolean : true if the entity is removed
     * @throws IdNotFoundException : id not found
     * @throws IdIsNullException : id is null
     */
    boolean remove(E entity) throws IdNotFoundException, IdIsNullException;
    /**
     * Remove an entity by id
     * @param id : id of the entity to remove
     * @return boolean : true if the entity is removed
     * @throws IdNotFoundException : id not found
     * @throws IdIsNullException : id is null
     */
    boolean removeById(ID id) throws IdNotFoundException, IdIsNullException;
    /**
     * Restore an entity by id
     * @param id : id of the entity to restore
     * @return boolean : true if the entity is restored
     * @throws IdNotFoundException : id not found
     * @throws IdIsNullException : id is null
     */
    boolean restore(ID id) throws IdNotFoundException, IdIsNullException;
    /**
     * Check if an entity exist by id
     * @param id : id of the entity
     * @return boolean : true if the entity exist
     * @throws IdNotFoundException : id not found
     * @throws IdIsNullException : id is null
     */
    E isExist(ID id) throws IdNotFoundException, IdIsNullException;
    /**
     * Check if an entity is removed by id
     * @param id : id of the entity
     * @return boolean : true if the entity is removed
     * @throws IdNotFoundException : id not found
     */
    boolean isRemove(ID id) throws IdNotFoundException, IdIsNullException;
    /**
     * Find an entity by id
     * @param id : id of the entity
     * @return Optional of the entity
     * @throws IdNotFoundException : id not found
     * @throws IdIsNullException : id is null
     */
    E findById(ID id) throws IdNotFoundException, IdIsNullException;
    /**
     * Find and remove an entity by id
     * @param id : id of the entity
     * @return Optional of the entity
     * @throws IdNotFoundException : id not found
     * @throws IdIsNullException : id is null
     */
    E findRemoveById(ID id) throws IdNotFoundException, IdIsNullException;
    /**
     * Find all entities
     * @return List of entities
     */
    List<E> findAll();
    /**
     * Find all entities removed
     * @return List of entities
     */
    List<E> findAllRemove();


}
