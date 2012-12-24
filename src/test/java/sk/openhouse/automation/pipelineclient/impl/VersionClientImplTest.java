package sk.openhouse.automation.pipelineclient.impl;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class VersionClientImplTest {

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
    public void testGetVersions() {

        String host = "http://localhost";
        String projectName = "test_project";
        String expectedUri = "http://localhost/projects/test_project/versions";
        testCallUri(host, projectName, expectedUri);
    }

    @Test
    public void testGetVersionsTrailingSlash() {

        String host = "http://localhost/";
        String projectName = "test_project";
        String expectedUri = "http://localhost/projects/test_project/versions";
        testCallUri(host, projectName, expectedUri);
    }

    private void testCallUri(String host, String projectName, String expectedUri) {

        VersionClientImpl versionClientImpl = new VersionClientImpl(client, host);
        versionClientImpl.getVersions(projectName);
        Mockito.verify(client, Mockito.times(1)).resource(Mockito.eq(expectedUri));
    }
}
