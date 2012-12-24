package sk.openhouse.automation.pipelineclient.impl;

import org.apache.commons.lang3.StringUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import sk.openhouse.automation.pipelineclient.BuildClient;
import sk.openhouse.automation.pipelinedomain.domain.response.BuildsResponse;

public class BuildClientImpl implements BuildClient {

    /** uri part to retrieve all builds */
    private static final String BUILDS_URI_PART = "/projects/%s/versions/%s/builds";

    private final String resourceTemplate;
    private final Client client;

    public BuildClientImpl(Client client, String host) {

        host = StringUtils.removeEnd(host, "/");
        resourceTemplate = host + BUILDS_URI_PART;
        this.client = client;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuildsResponse getBuilds(String projectName, String versionNumber) {

        String resource = String.format(resourceTemplate, projectName, versionNumber);
        WebResource webResource = client.resource(resource);
        return webResource.get(BuildsResponse.class);
    }
}
