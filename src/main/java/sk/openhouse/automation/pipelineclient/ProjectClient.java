package sk.openhouse.automation.pipelineclient;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

import sk.openhouse.automation.pipelinedomain.domain.request.ProjectRequest;
import sk.openhouse.automation.pipelinedomain.domain.response.ProjectsResponse;

/**
 * 
 * @author pete
 */
public interface ProjectClient {

    /**
     * @return all projects
     * @throws UniformInterfaceException if the status of the HTTP response is greater than or equal to 300
     * @throws ClientHandlerException if the client handler fails to process the request or response.
     */
    ProjectsResponse getProjects() throws UniformInterfaceException, ClientHandlerException;

    /**
     * @param projectName name of the project to be added
     * @return true if the project has been added successfully (response code is between 200 - 299 range)
     * @throws ClientHandlerException if the client handler fails to process the request or response.
     */
    boolean addProject(String projectName) throws ClientHandlerException;

    /**
     * Updates existing project
     *
     * @param projectName name of the project that will be updated
     * @param project data to be updated
     * @return true if the project has been updated successfully (response code is between 200 - 299 range)
     * @throws ClientHandlerException if the client handler fails to process the request or response.
     */
    boolean updateProject(String projectName, ProjectRequest project) throws ClientHandlerException;
}
