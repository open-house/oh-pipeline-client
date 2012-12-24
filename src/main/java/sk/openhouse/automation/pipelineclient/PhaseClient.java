package sk.openhouse.automation.pipelineclient;

import sk.openhouse.automation.pipelinedomain.domain.response.PhasesResponse;

/**
 * 
 * @author pete
 */
public interface PhaseClient {

    PhasesResponse getPhases(String projectName, String versionNumber);
}
