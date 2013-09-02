/**
 * 
 */
package com.apps.sapps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.sapps.orm.model.User;
import com.apps.sapps.orm.model.manager.UserManager;

/**
 * @author bhuvan.t
 *
 */
@Service
public class MyResourceService
{
    private static final Logger logger = LoggerFactory.getLogger(MyResourceService.class);

    @Autowired
    private UserManager userManager;
    public String getIt(){
        
        User user = userManager.get(1L);
        StringBuilder sb = new StringBuilder();
        
        sb.append("Username : "+user.getUsername());
        sb.append("first name : "+user.getFirstName());
        sb.append("Last Name : "+user.getLastName());
        sb.append("Email : "+user.getEmail());
        sb.append( "Got it!");
        logger.debug(sb.toString());
        return  sb.toString();
    }
}
