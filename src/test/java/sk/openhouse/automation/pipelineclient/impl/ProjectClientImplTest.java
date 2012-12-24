package sk.openhouse.automation.pipelineclient.impl;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import sk.openhouse.automation.pipelinedomain.domain.response.ProjectsResponse;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class ProjectClientImplTest {

    @Mock
    private Client client;

    @Mock
    private WebResource webResource;

    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConstruct() {

        new ProjectClientImpl(client, "http://localhost");
        Mockito.verify(client, Mockito.times(1)).resource("http://localhost/projects");
    }

    @Test
    public void testConstructTrailingSlash() {

        /* host ends with slash, should be removed */
        new ProjectClientImpl(client, "http://localhost/");
        Mockito.verify(client, Mockito.times(1)).resource("http://localhost/projects");
    }

    @Test
    public void testGetProjects() {

        Mockito.when(client.resource(Mockito.anyString())).thenReturn(webResource);
        ProjectClientImpl projectClientImpl = new ProjectClientImpl(client, "http://localhost");

        projectClientImpl.getProjects();
        Mockito.verify(webResource, Mockito.times(1)).get(ProjectsResponse.class);
    }
}
