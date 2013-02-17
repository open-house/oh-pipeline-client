package sk.openhouse.automation.pipelineclient.impl;

import com.sun.jersey.api.client.*;
import org.apache.commons.lang3.StringUtils;

import sk.openhouse.automation.pipelineclient.ProjectClient;
import sk.openhouse.automation.pipelinedomain.domain.request.ProjectRequest;
import sk.openhouse.automation.pipelinedomain.domain.response.ProjectsResponse;

public class ProjectClientImpl implements ProjectClient {

    /** uri part to retrieve all builds */
    private static final String PROJECTS_URI_PART = "/projects";

    private final Client client;
    private final WebResource projectsWebResource;

    public ProjectClientImpl(Client client, String host) {

        this.client = client;
        host = StringUtils.removeEnd(host, "/");
        projectsWebResource = client.resource(host + PROJECTS_URI_PART);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectsResponse getProjects() {
        return projectsWebResource.get(ProjectsResponse.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addProject(String projectName) throws UniformInterfaceException, ClientHandlerException {

        String projectsUri = StringUtils.removeEnd(projectsWebResource.getURI().toString(), "/");
        WebResource projectWebResource = client.resource(String.format("%s/%s", projectsUri, projectName));
        int responseCode = projectWebResource.put(ClientResponse.class).getStatus();

        return (responseCode >= 200 && responseCode < 300);
    }
}
