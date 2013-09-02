package com.apps.sapps;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Root resource (exposed at "myresource" path)
 */
@Component
@Path("myresource")
public class MyResource
{

    private static final Logger logger = LoggerFactory.getLogger(MyResource.class);
    @Value("${myResource.username}")
    private String username;
    @Autowired
    private MyResourceService myResourceService;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     * 
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt()
    {
        logger.info(" Property value username : " + getUsername());
        System.out.println(" Property value username : " + getUsername());
        return myResourceService.getIt() + " " + getUsername();
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return the myResourceService
     */

    public MyResourceService getMyResourceService()
    {
        return myResourceService;
    }

    /**
     * @param myResourceService
     *            the myResourceService to set
     */

    public void setMyResourceService(MyResourceService myResourceService)
    {
        this.myResourceService = myResourceService;
    }

}
