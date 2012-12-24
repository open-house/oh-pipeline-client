package sk.openhouse.automation.pipelineclient.impl;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class BuildClientImplTest {

    @Mock
    private Client client;

    @Mock
    private WebResource webResource;

    @BeforeMethod
    public void beforeMethod() {

        MockitoAnnotations.initMocks(this);
        Mockito.when(client.resource(Mockito.anyString())).thenReturn(webResource);
    }

    @Test
    public void testGetBuilds() {

        String host = "http://localhost";
        String projectName = "test_project";
        String versionNumber = "0.1";
        String expectedUri = "http://localhost/projects/test_project/versions/0.1/builds";
        testCallUri(host, projectName, versionNumber, expectedUri);
    }

    @Test
    public void testGetVersionsTrailingSlash() {

        String host = "http://localhost/";
        String projectName = "test_project";
        String versionNumber = "0.1";
        String expectedUri = "http://localhost/projects/test_project/versions/0.1/builds";
        testCallUri(host, projectName, versionNumber, expectedUri);
    }

    private void testCallUri(String host, String projectName, String versionNumber, String expectedUri) {

        BuildClientImpl buildClientImpl = new BuildClientImpl(client, host);
        buildClientImpl.getBuilds(projectName, versionNumber);
        Mockito.verify(client, Mockito.times(1)).resource(Mockito.eq(expectedUri));
    }
}
