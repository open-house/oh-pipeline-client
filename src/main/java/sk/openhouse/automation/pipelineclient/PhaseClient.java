package sk.openhouse.automation.pipelineclient;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

import sk.openhouse.automation.pipelinedomain.domain.response.PhasesResponse;

/**
 * 
 * @author pete
 */
public interface PhaseClient {

    /**
     * @param projectName
     * @param versionNumber
     * @return all the phases for specified project and version
     * @throws UniformInterfaceException if the status of the HTTP response is greater than or equal to 300
     * @throws ClientHandlerException if the client handler fails to process the request or response.;
     */
    PhasesResponse getPhases(String projectName, String versionNumber)
            throws UniformInterfaceException, ClientHandlerException;
}
