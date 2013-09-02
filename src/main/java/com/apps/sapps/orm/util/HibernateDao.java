package com.apps.sapps.orm.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bhuvan
 * 
 */
@Repository
@Qualifier("dao")
public class HibernateDao implements IDao
{
    private static final Log s_log = LogFactory.getLog(HibernateDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    /**
     * @see UBQoolTextDao.delete This deletes entity Object
     * @param entity
     *            entity
     * @throws DataAccessException
     *             DataAccessException
     */
    public void delete(Object entity) throws DataAccessException
    {
        getSession().delete(entity);
    }

    /*
     * @see IDao.deleteAll
     */
    public void deleteAll(Collection entities) throws DataAccessException
    {
        Session session = getSession();
        for (Object entity : entities)
        {
            session.delete(entity);
        }
    }

    /*
     * @see IDao.find
     */
    @Transactional
    public List find(String query, Object... values) throws DataAccessException
    {
        Session session = getSession();
        Query queryObject = session.createQuery(query);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject.list();
    }

    /*
     * @see IDao.load
     */
    @SuppressWarnings("unchecked")
    public Object load(Class type, Serializable classId) throws DataAccessException
    {
        return getSession().load(type, classId);
    }

    /*
     * @see IDao.load
     */
    @SuppressWarnings("unchecked")
    public List loadAll(Class type) throws DataAccessException
    {
        return null; // getSession().loadAll(type);
    }

    /*
     * @see IDao.saveOrUpdate
     */
    public void saveOrUpdate(Object entity) throws DataAccessException
    {
        getSession().saveOrUpdate(entity);
    }

    /*
     * @see IDao.saveOrUpdate
     */
    @SuppressWarnings("unchecked")
    public void saveOrUpdateAll(Collection entities) throws DataAccessException
    {
        Session session = getSession();
        for (Object entity : entities)
        {
            session.saveOrUpdate(entity);
        }
    }

    /*
     * @see IDao.flush
     */
    public void flush()
    {
        getSession().flush();
    }

    /*
     * @see IDao.clear
     */
    public void clear()
    {
        getSession().clear();
    }

    /*
     * @see IDao.merge
     */
    public void merge(Object object) throws DataAccessException
    {
        getSession().merge(object);
    }

    /*
     * @see IDao.refresh
     */
    public void refresh(Object object)
    {
        getSession().refresh(object);
    }

    @SuppressWarnings("unchecked")
    public List findByCriteria(DetachedCriteria criteria)
    {
        return null ; //getSession().findByCriteria(criteria);
    }
    
    @Transactional
    public Object get(Class type, Serializable classId)
    {
        return getSession().get(type, classId);
    }

    /**
     * returns a List by querying using start and limit values
     */
    @SuppressWarnings("rawtypes")
    public List findByFilter(String sql)
    {
        Query query = getSession().createQuery(sql);

        // query.setFirstResult(Integer.parseInt(GridOptions.getGridOptions().getStartValue()));
        // query.setMaxResults(Integer.parseInt(GridOptions.getGridOptions().getLimitValue()));
        List labels = query.list();
        // session.close();
        return labels;
    }

    public List findByNativeSql(String sql, Class entityClassName)
    {
        Query query = getSession().createSQLQuery(sql).addEntity(entityClassName);
        return query.list();
    }

    @Transactional
    public Object getCount(String sql)
    {
        try
        {
            Query query = getSession().createQuery(sql);
            List list = query.list();
            // session.close();
            return list.get(0);
        } catch (DataAccessException e)
        {
            s_log.error(e.getMessage(), e);
            return null;
        }
    }

    public List findByStartAndEndIndex(String sql, int startIndex, int endIndex) throws DataAccessException
    {
        Query query = getSession().createQuery(sql);
        query.setFirstResult(Integer.valueOf(startIndex));
        query.setMaxResults(Integer.valueOf(endIndex));
        return query.list();
    }

    public Object getColumnValue(String sql)
    {
        try
        {
            Query query = getSession().createQuery(sql);
            List list = query.list();
            // session.close();
            s_log.info("list : " + list);
            return list.get(0);
        } catch (DataAccessException e)
        {
            s_log.error(e.getMessage(), e);
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    public List findByNativeSql(String sql, Class entityClassName, int start, int limit)
    {
        try
        {
            Query query = getSession().createSQLQuery(sql).addEntity(entityClassName);
            query.setFirstResult(Integer.valueOf(start));
            query.setMaxResults(Integer.valueOf(limit));
            return query.list();
        } catch (Exception e)
        {
            s_log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * fetches count by given sql query
     * 
     * @param sql
     *            sqlQuery
     * @return
     */
    public Object getCountBySqlQuery(String sql)
    {
        return null;
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    /**
     * @param sessionFactory
     *            the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @return the session
     */
    public Session getSession()
    {
        return getSessionFactory().getCurrentSession();
    }

    /**
     * @param session
     *            the session to set
     */
    public void setSession(Session session)
    {
        this.session = session;
    }

    /*
     * public Object getCountBySqlQuery(String sql)
     * {
     * try
     * {
     * Session session = getHibernateTemplate().getSessionFactory().getCurrentSession(); // openSession();
     * Query query = session.createSQLQuery(sql);
     * List list = query.list();
     * return (list != null) ? StringUtils.parseLong(list.get(0) + "") : 0;
     * } catch (Exception e)
     * {
     * s_log.error(e.getMessage(), e);
     * return 0;
     * }
     * }
     */

}