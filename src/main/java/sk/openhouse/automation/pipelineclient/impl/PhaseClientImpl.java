package sk.openhouse.automation.pipelineclient.impl;

import org.apache.commons.lang3.StringUtils;

import sk.openhouse.automation.pipelineclient.PhaseClient;
import sk.openhouse.automation.pipelinedomain.domain.response.PhasesResponse;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class PhaseClientImpl implements PhaseClient {

    /** uri part to retrieve all builds */
    private static final String PHASES_URI_PART = "/projects/%s/versions/%s/phases";

    private final String resourceTemplate;
    private final Client client;

    public PhaseClientImpl(Client client, String host) {

        host = StringUtils.removeEnd(host, "/");
        resourceTemplate = host + PHASES_URI_PART;
        this.client = client;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhasesResponse getPhases(String projectName, String versionNumber) {

        String resource = String.format(resourceTemplate, projectName, versionNumber);
        WebResource webResource = client.resource(resource);
        return webResource.get(PhasesResponse.class);
    }
}
