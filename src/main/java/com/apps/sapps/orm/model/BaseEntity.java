package com.apps.sapps.orm.model;


import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * 
 * @author Bhuvan Raj T
 * 
 */
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class BaseEntity implements Serializable
{
    @Id
    @GeneratedValue
    protected Long id;

    @Column(unique = true, nullable = false, updatable = false, length = 36)
    protected String uuid = UUID.randomUUID().toString();

    public Long getId()
    {
        return id;
    }

    public void setId(Long databaseId)
    {
        this.id = databaseId;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = UUID.fromString(uuid).toString();
    }

    /**
     * The current implementation makes a loose check for class equivalence. We cannot make a simple class equality check as CGLIB creates a proxy
     * class with a difference name. Because of the proxy class, we need to prevent referring directly to non final fields from final methods and use
     * getters instead.
     */
    @Override
    public final boolean equals(Object other)
    {
        if (other instanceof BaseEntity)
        {
            return (other != null && getUuid().equals(((BaseEntity) other).getUuid()))
                    && ((other.getClass().isAssignableFrom(this.getClass()))
                            || (this.getClass().isAssignableFrom(other.getClass())));
        }

        return false;
    }

    @Override
    public final int hashCode()
    {
        return getUuid().hashCode();
    }

    @Override
    public String toString()
    {
        return new StringBuffer().append('[').append(getClass().getName()).append(' ').append(getUuid()).append(']').toString();
    }
}
