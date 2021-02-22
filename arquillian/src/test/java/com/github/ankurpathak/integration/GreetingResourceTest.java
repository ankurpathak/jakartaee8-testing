package com.github.ankurpathak.integration;

import com.github.ankurpathak.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class GreetingResourceTest {
   // private final static Logger LOGGER = Logger.getLogger(GreetingResourceTest.class.getName());
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return
        ShrinkWrap.create(WebArchive.class)
                .addClass(GreetingMessage.class)
                .addClass(GreetingService.class).addClass(SimpleGreetingService.class)
                .addClasses(GreetingResource.class, JaxrsActivator.class)
                // Enable CDI
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @ArquillianResource
    private URL base;
    @Before
    public void setup()  {
        try {
            Class<?> clazz = Class.forName("com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider");
        } catch (ClassNotFoundException e) {
            System.out.println("Only use for OpenLiberty/CXF which does not register a json provider automatically.");
        }
    }

    @Test
    public void should_create_greetingTest() throws MalformedURLException {
        Response response = RestAssured.get(new URL(base, "api/greeting/JakartaEE"));
        assertEquals("response status is ok", 200, response.getStatusCode());
        assertTrue("message should start with \"Say Hello to JakartaEE at \"",
                response.as(GreetingMessage.class).getMessage().startsWith("Say Hello to JakartaEE"));

    }
}