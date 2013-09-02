/**
 * 
 */
package com.apps.sapps.orm.model.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.apps.sapps.orm.model.User;

/**
 * @author bhuvan.t
 *
 */
@Repository
public class UserManager extends GenericManager<User, Long>
{
    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);

    public UserManager()
    {
        super(User.class);
    }
}
