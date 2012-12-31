package sk.openhouse.automation.pipelineclient;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

import sk.openhouse.automation.pipelinedomain.domain.response.ProjectsResponse;

/**
 * 
 * @author pete
 */
public interface ProjectClient {

    /**
     * @return all projects
     * @throws UniformInterfaceException if the status of the HTTP response is greater than or equal to 300
     * @throws ClientHandlerException if the client handler fails to process the request or response.;
     */
    ProjectsResponse getProjects() throws UniformInterfaceException, ClientHandlerException;
}
