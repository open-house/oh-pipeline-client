package sk.openhouse.automation.pipelineclient;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

import sk.openhouse.automation.pipelinedomain.domain.response.VersionsResponse;

/**
 * 
 * @author pete
 */
public interface VersionClient {

    /**
     * @param projectName
     * @return all the versions for specified project
     * @throws UniformInterfaceException if the status of the HTTP response is greater than or equal to 300
     * @throws ClientHandlerException if the client handler fails to process the request or response.;
     */
    VersionsResponse getVersions(String projectName) throws UniformInterfaceException, ClientHandlerException;
}
