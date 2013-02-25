package sk.openhouse.automation.pipelineclient.impl;

import com.sun.jersey.api.client.ClientResponse;
import junit.framework.Assert;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import sk.openhouse.automation.pipelinedomain.domain.request.ProjectRequest;
import sk.openhouse.automation.pipelinedomain.domain.response.ProjectsResponse;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class ProjectClientImplTest {

    @Mock
    private Client client;

    @Mock
    private WebResource webResource;

    @Mock
    private Builder builder;

    @Mock
    private ClientResponse clientResponse;

    private ProjectClientImpl projectClientImpl;

    @BeforeMethod
    public void beforeMethod() {

        MockitoAnnotations.initMocks(this);
        Mockito.when(client.resource(Mockito.anyString())).thenReturn(webResource);
        Mockito.when(webResource.type(Mockito.anyString())).thenReturn(builder);
        Mockito.when(webResource.put(ClientResponse.class)).thenReturn(clientResponse);
        Mockito.when(builder.entity(Mockito.anyString())).thenReturn(builder);
        Mockito.when(builder.post(ClientResponse.class)).thenReturn(clientResponse);

        projectClientImpl = new ProjectClientImpl(client, "http://localhost");
    }

    @Test
    public void testConstruct() {

        new ProjectClientImpl(client, "http://localhost");
        Mockito.verify(client, Mockito.atLeast(1)).resource("http://localhost/projects");
    }

    @Test
    public void testConstructTrailingSlash() {

        /* host ends with slash, should be removed */
        new ProjectClientImpl(client, "http://localhost/");
        Mockito.verify(client, Mockito.atLeast(1)).resource("http://localhost/projects");
    }

    @Test
    public void testGetProjects() {

        projectClientImpl.getProjects();
        Mockito.verify(webResource, Mockito.times(1)).get(ProjectsResponse.class);
    }

    @Test
    public void testAddProject() {

        String projectName = "test_project";
        Mockito.when(clientResponse.getStatus()).thenReturn(201);

        boolean response = projectClientImpl.addProject(projectName);
        Assert.assertTrue(response);
        Mockito.verify(webResource, Mockito.times(1)).put(ClientResponse.class);
    }

    @Test
    public void testAddProjectFail() {

        String projectName = "test_project";
        Mockito.when(clientResponse.getStatus()).thenReturn(404);

        Assert.assertFalse(projectClientImpl.addProject(projectName));
    }

    @Test
    public void testUpdateProject() {

        ProjectRequest request = new ProjectRequest();
        request.setName("new_name");
        Mockito.when(clientResponse.getStatus()).thenReturn(201);

        boolean response = projectClientImpl.updateProject("test_project", request);
        Assert.assertTrue(response);
        Mockito.verify(builder, Mockito.times(1)).post(ClientResponse.class);
    }

    @Test
    public void testUpdateProjectFail() {

        ProjectRequest request = new ProjectRequest();
        request.setName("new_name");
        Mockito.when(clientResponse.getStatus()).thenReturn(501);

        Assert.assertFalse(projectClientImpl.updateProject("test_project", request));
    }
}
