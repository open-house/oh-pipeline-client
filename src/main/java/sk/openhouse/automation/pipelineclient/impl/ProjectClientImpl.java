package sk.openhouse.automation.pipelineclient.impl;

import com.sun.jersey.api.client.*;
import org.apache.commons.lang3.StringUtils;

import sk.openhouse.automation.pipelineclient.ProjectClient;
import sk.openhouse.automation.pipelinedomain.domain.request.ProjectRequest;
import sk.openhouse.automation.pipelinedomain.domain.response.ProjectsResponse;

import javax.ws.rs.core.MediaType;

public class ProjectClientImpl implements ProjectClient {

    /** uri part to retrieve all builds */
    private static final String PROJECTS_URI_PART = "/projects";

    private final Client client;
    private final String projectsUri;
    private final WebResource projectsWebResource;

    public ProjectClientImpl(Client client, String host) {

        this.client = client;
        projectsUri = StringUtils.removeEnd(host, "/") + PROJECTS_URI_PART;
        projectsWebResource = client.resource(projectsUri);
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
    public boolean addProject(String projectName) throws ClientHandlerException {

        WebResource projectWebResource = getProjectWebResource(projectName);
        int responseCode = projectWebResource.put(ClientResponse.class).getStatus();

        return (responseCode >= 200 && responseCode < 300);
    }

    @Override
    public boolean updateProject(String projectName, ProjectRequest project) throws ClientHandlerException {

        WebResource projectWebResource = getProjectWebResource(projectName);
        int responseCode = projectWebResource
                .type(MediaType.APPLICATION_XML)
                .entity(project)
                .post(ClientResponse.class)
                .getStatus();

        return (responseCode >= 200 && responseCode < 300);
    }

    private WebResource getProjectWebResource(String projectName) {
        return client.resource(String.format("%s/%s", projectsUri, projectName));
    }
}
