package sk.openhouse.automation.pipelineclient;

import sk.openhouse.automation.pipelinedomain.domain.response.ProjectsResponse;

/**
 * 
 * @author pete
 */
public interface ProjectClient {

    ProjectsResponse getProjects();
}
