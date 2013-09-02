/**
 * 
 */
package com.apps.sapps.orm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author bhuvan.t
 * 
 */
@Entity
public class User extends BaseEntity
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Column(columnDefinition = "CHAR(12)", length = 10)
    private String code;
    @Column(length = 30, nullable = false)
    private String username;
    @Column(length = 30, nullable = false)
    private String password;
    @Column(length = 30, nullable = false)
    private String firstName;
    @Column(length = 30, nullable = false)
    private String lastName;
    @Column(length = 60, nullable = false)
    private String email;
    @Column(length = 30, nullable = false)
    private String mobileNumber;
    @Column(length = 30, nullable = false)
    private String gender;
    @Column(nullable = false)
    private Timestamp createdOn;
    
    @Column
    private Timestamp updatedOn;

    /**
     * @return the code
     */
    public String getCode()
    {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @return the mobileNumber
     */
    public String getMobileNumber()
    {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return the gender
     */
    public String getGender()
    {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    /**
     * @return the createdOn
     */
    public Timestamp getCreatedOn()
    {
        return createdOn;
    }

    /**
     * @param createdOn the createdOn to set
     */
    public void setCreatedOn(Timestamp createdOn)
    {
        this.createdOn = createdOn;
    }

    /**
     * @return the updatedOn
     */
    public Timestamp getUpdatedOn()
    {
        return updatedOn;
    }

    /**
     * @param updatedOn the updatedOn to set
     */
    public void setUpdatedOn(Timestamp updatedOn)
    {
        this.updatedOn = updatedOn;
    }
    
    
}
