package sk.openhouse.automation.pipelineclient.impl;

import org.apache.commons.lang3.StringUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import sk.openhouse.automation.pipelineclient.VersionClient;
import sk.openhouse.automation.pipelinedomain.domain.response.VersionsResponse;

public class VersionClientImpl implements VersionClient {

    /** uri part to retrieve all versions */
    private static final String VERSIONS_URI_PART = "/projects/%s/versions";

    private final String resourceTemplate;
    private final Client client;

    public VersionClientImpl(Client client, String host) {

        host = StringUtils.removeEnd(host, "/");
        resourceTemplate = host + VERSIONS_URI_PART;
        this.client = client;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionsResponse getVersions(String projectName) {

        String resource = String.format(resourceTemplate, projectName);
        WebResource webResource = client.resource(resource);
        return webResource.get(VersionsResponse.class);
    }
}
