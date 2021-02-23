package com.github.ankurpathak.integration;

import com.github.ankurpathak.TestFilter;
import com.github.ankurpathak.TestServlet;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class FilterServletTest {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(
                        TestFilter.class,
                        TestServlet.class
                        );
    }

    @ArquillianResource
    private URL base;


    @Test
    @RunAsClient
    public void standard_servlet_should_return_simple_text() throws MalformedURLException {
        Response response = RestAssured.get(new URL(base, "TestServlet"));
        assertThat(response.getBody().print(), is(equalTo("Hello World!")));
    }
}