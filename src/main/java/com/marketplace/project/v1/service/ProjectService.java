package com.marketplace.project.v1.service;

import com.marketplace.project.v1.data.Project;
import com.marketplace.project.v1.ex.ProjectCreateException;
import common.ex.NotFoundException;

/**
 * Created by dipen.bhatt on 3/25/18.
 */
public interface ProjectService {

    Project findProjectById(Long id) throws NotFoundException;
    Long createProject(Project project) throws ProjectCreateException;
}
