package sk.openhouse.automation.pipelineclient;

import sk.openhouse.automation.pipelinedomain.domain.response.BuildsResponse;

public interface BuildClient {

    BuildsResponse getBuilds(String projectName, String versionNumber);
}
