/**
 *
 */
package com.apps.sapps.orm.model.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import com.apps.sapps.orm.util.DAOUtils;
import com.apps.sapps.orm.util.IDao;

/**
 * @author bhuvan
 * 
 */
public class GenericManager<T, PK extends Serializable>
{
    private static final String UNCHECKED = "unchecked";

    /** The m_dao. */
    @Autowired
    @Qualifier("dao")
    protected IDao m_dao;

    /** The m_type. */
    protected Class<T> m_type;

    /**
     * Instantiates a new generic manager.
     * 
     * @param type
     *            the type
     */
    public GenericManager(Class<T> type)
    {
        super();
        m_type = type;
    }

    /**
     * Set the DAO for use by this instance.
     * 
     * @param dao
     *            the dao
     */
    public void setDao(IDao dao)
    {
        m_dao = dao;
    }

    /**
     * Get the DAO in use by this instance.
     * 
     * @return the dao
     */
    public IDao getDao()
    {
        return m_dao;
    }

    /**
     * Delete.
     * 
     * @param entity
     *            the entity
     * 
     * 
     * 
     * @see IDataAccessObject.delete
     */
    @Transactional
    public void delete(T entity)
    {
        m_dao.delete(entity);
    }

    /**
     * Re-implementation of the load method on IDataAccessObject that doesn't
     * require the class to be passed.
     * 
     * @param pkid
     *            the id
     * 
     * @return the T
     * 
     * 
     */
    @SuppressWarnings(UNCHECKED)
    public T load(PK pkid)
    {
        T result = null;
        try
        {
            result = (T) m_dao.load(m_type, pkid);
        } catch (ObjectRetrievalFailureException orfe)
        {

        }
        // Result should never be null here due to semantics of the load()
        // method on the DAO.
        return result;
    }

    /**
     * Save or update.
     * 
     * @param entity
     *            the entity
     * 
     * 
     * 
     * @see IDataAccessObject.saveOrUpdate
     */
    @Transactional
    public void saveOrUpdate(T entity)
    {
        m_dao.saveOrUpdate(entity);
    }

    @SuppressWarnings(UNCHECKED)
    @Transactional
    public void uncheckedsaveOrUpdate(Object entity)
    {
        m_dao.saveOrUpdate(entity);
    }

    /**
     * Save or update all.
     * 
     * @param entities
     *            the entities
     * 
     * 
     * 
     * @see IDataAccessObject.saveOrUpdateAll
     */
    @Transactional
    public void saveOrUpdateAll(Collection<T> entities)
    {
        m_dao.saveOrUpdateAll(entities);
    }

    /**
     * Delete all.
     * 
     * @param entities
     *            the entities
     * 
     * 
     * 
     * @see IDataAccessObject.deleteAll
     */
    @Transactional
    public void deleteAll(Collection<T> entities)
    {
        m_dao.deleteAll(entities);
    }

    /**
     * Find.
     * 
     * @param query
     *            the query
     * @param value
     *            the value
     * 
     * @return the list< t>
     * 
     * 
     * 
     * @see IDataAccessObject.find
     */
    @Transactional
    @SuppressWarnings(UNCHECKED)
    public List<T> find(String query, Object value)
    {
        return m_dao.find(query, value);
    }

    /**
     * Find.
     * 
     * @param query
     *            the query
     * @param values
     *            the values
     * 
     * @return the list< t>
     * 
     * 
     * 
     * @see IDataAccessObject.find
     */
    @Transactional
    @SuppressWarnings(UNCHECKED)
    public List<T> find(String query, Object... values)
    {
        return m_dao.find(query, values);
    }

    /**
     * This method is a sibling of the normal find, just that it is unchecked.
     * This can be used for making select queries which return a result, other
     * than a List<T>. e.g. stats about the object we are managing, or some
     * child object etc.
     * 
     * @param query
     *            the query
     * @param values
     *            the values
     * 
     * @return the list
     * 
     * 
     * 
     * @see GenericManager#find(String, Object...)
     */
    @SuppressWarnings(UNCHECKED)
    public List uncheckedFind(String query, Object... values)
    {
        return m_dao.find(query, values);
    }

    /**
     * Re-implementation of the IDataAccessObject.loadAll method that doesn't
     * require the type parameter.
     * 
     * @return the list< t>
     * 
     * 
     */
    @SuppressWarnings(UNCHECKED)
    public List<T> loadAll()
    {

        /*
         * TODO : There's a bug in Hibernate where in some instances (e.g.
         * bidirectional relationship with eager loading) multiple instances of
         * the same entity may be returned from loadAll(). This is a temporary
         * fix to this problem. Need to find a solution to this
         */
        List<T> result = new ArrayList<T>(new LinkedHashSet<T>(m_dao.loadAll(m_type)));
        return result;
    }

    public T get(PK pkid)
    {
        T result = null;
        try
        {
            result = (T) m_dao.get(m_type, pkid);
        } catch (ObjectRetrievalFailureException orfe)
        {

        }
        // Result should never be null here due to semantics of the load()
        // method on the DAO.
        return result;
    }

    /**
     * Flush.
     * 
     * @see IDataAccessObject.flush
     */
    public void flush()
    {
        m_dao.flush();
    }

    /**
     * Merge.
     * 
     * @param entity
     *            the entity
     * 
     * @see IDataAccessObject.merge
     */
    public void merge(T entity)
    {
        m_dao.merge(entity);
    }

    /**
     * Convenience method to get the first result from a list.
     * 
     * @param entities
     *            The List from which to get the result.
     * 
     * @return The first object in the List, or null if the List is null or
     *         empty.
     */
    protected T first(List<T> entities)
    {
        return DAOUtils.first(entities);
    }

    /**
     * Convenience method to get a unique result from a list, using 'load'
     * semantics.
     * 
     * @param entries
     *            the entries
     * @param params
     *            the params
     * 
     * @return The first object in the List, or null if the List is null or
     *         empty.
     * 
     * @throws DataAccessException
     *             if the object is not found or not unique.
     */
    @Transactional
    protected T unique(List<T> entries, Object... params) throws DataAccessException
    {
        return DAOUtils.unique(entries, getClass(), params);
    }

    /**
     * 
     * @param sql
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List findByFilter(String sql)
    {
        return m_dao.findByFilter(sql);
    }

    public List<T> findByCriteria(DetachedCriteria criteria)
    {
        return m_dao.findByCriteria(criteria);
    }

    @Transactional
    public Object getCount(String sql)
    {
        return m_dao.getCount(sql);
    }

    public Object getSum(String sql)
    {
        return m_dao.getCount(sql);
    }

    public List findByNativeSql(String sql)
    {
        return m_dao.findByNativeSql(sql, m_type);
    }

    public List findByStartAndEndIndex(String sql, int startRow, int endRow) throws DataAccessException
    {
        return m_dao.findByStartAndEndIndex(sql, startRow, endRow);
    }

    public List findByNativeSql(String sql, int start, int limit) throws DataAccessException
    {
        return m_dao.findByNativeSql(sql, m_type, start, limit);
    }

    public Object getColumnValue(String sql)
    {
        return m_dao.getColumnValue(sql);
    }

    public Object getCountBySqlQuery(String sql)
    {
        return m_dao.getCountBySqlQuery(sql);
    }
    
    public int userTotalFilterRecords(String filterString, String entityName)
    {
        String sql = "select count(*) from " + entityName + " where 1=1  " + filterString;
        List<T> entityList = find(sql);
        if (entityList.get(0) != null && entityList.size() > 0)
        {
            Object objValue = (Object) entityList.get(0);
            return Integer.parseInt(objValue.toString());
        }
        return 0;
    }

}
