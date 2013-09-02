/**
 *
 */
package com.apps.sapps.orm.util;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author bhuvan
 * 
 */
public interface IDao
{

    /**
     * Delete.
     * 
     * @param entity the entity to Delete
     * 
     * @throws DataAccessException the data access exception
     */
    void delete(Object entity) throws DataAccessException;

    /**
     * Delete.
     * 
     * @param entities Entities to delete
     * @throws DataAccessException
     */
    void deleteAll(Collection entities) throws DataAccessException;

    /**
     * Find objects satisfying the query
     * 
     * @param query the query
     * @param values the parameter values
     * 
     * @return the list of objects satisfying the query
     * 
     * @throws DataAccessException the data access exception
     */
    
    @Transactional
    List find(String query, Object... values) throws DataAccessException;

    /**
     * Load the object of specified type by id.
     * 
     * @param type the type of object
     * @param classId the id
     * 
     * @return the loaded object
     * 
     * @throws DataAccessException the data access exception
     */
    Object load(Class type, Serializable classId) throws DataAccessException;
	
    Object get(Class type, Serializable classId);

    /**
     * Load all objects of the specified type.
     * 
     * @param type the type of objects to load
     * 
     * @return the list of objecs
     * 
     * @throws DataAccessException the data access exception
     */
    List loadAll(Class type) throws DataAccessException;

    /**
     * Save or update (if already saved) the entity
     * 
     * @param entity the entity to save
     * 
     * @throws DataAccessException the data access exception
     */
    void saveOrUpdate(Object entity) throws DataAccessException;

    /**
     * Save or update all entities
     * 
     * @param entities the entities to save
     * 
     * @throws DataAccessException the data access exception
     */
    void saveOrUpdateAll(Collection entities) throws DataAccessException;

    /**
     * Merges the entity.
     * 
     * @param entity the entity to merge
     * 
     * @throws DataAccessException the data access exception
     */
    void merge(Object entity) throws DataAccessException;

    /**
     * Refreshes the entity
     * 
     * @param entity the entity
     * 
     * @throws DataAccessException the data access exception
     */
    void refresh(Object entity) throws DataAccessException;

    /**
     * Flushes the current session.
     */
    void flush();

    /**
     * Clears the current session.
     */
    void clear();
    
    /**
     * filters the data with start and limit values
     * @param sql
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List findByFilter(String sql);
    
    public List findByCriteria(DetachedCriteria criteria);
    public Object getCount(String sql);
    
    public List findByNativeSql(String sql, Class entityClassName);
    public List findByStartAndEndIndex(String sql, int startIndex, int endIndex) throws DataAccessException;
    
    public Object getColumnValue(String sql);
    public List findByNativeSql(String sql, Class entityClassName, int start, int limit);
    
    public Object getCountBySqlQuery(String sql);
}
