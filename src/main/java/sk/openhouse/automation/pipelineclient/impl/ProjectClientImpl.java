package sk.openhouse.automation.pipelineclient.impl;

import org.apache.commons.lang3.StringUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import sk.openhouse.automation.pipelineclient.ProjectClient;
import sk.openhouse.automation.pipelinedomain.domain.response.ProjectsResponse;

public class ProjectClientImpl implements ProjectClient {

    /** uri part to retrieve all builds */
    private static final String PROJECTS_URI_PART = "/projects";

    private final WebResource webResource;

    public ProjectClientImpl(Client client, String host) {

        host = StringUtils.removeEnd(host, "/");
        webResource = client.resource(host + PROJECTS_URI_PART);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectsResponse getProjects() {
        return webResource.get(ProjectsResponse.class);
    }
}
