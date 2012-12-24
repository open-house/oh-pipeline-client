package sk.openhouse.automation.pipelineclient;

import sk.openhouse.automation.pipelinedomain.domain.response.VersionsResponse;

/**
 * 
 * @author pete
 */
public interface VersionClient {

    VersionsResponse getVersions(String projectName);
}
