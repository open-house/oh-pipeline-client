package sk.openhouse.automation.pipelineclient;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

import sk.openhouse.automation.pipelinedomain.domain.response.BuildsResponse;

public interface BuildClient {

    /**
     * @param projectName
     * @param versionNumber
     * @return all the builds for specified project and version
     * @throws UniformInterfaceException if the status of the HTTP response is greater than or equal to 300
     * @throws ClientHandlerException if the client handler fails to process the request or response.;
     */
    BuildsResponse getBuilds(String projectName, String versionNumber) 
            throws UniformInterfaceException, ClientHandlerException;
}
