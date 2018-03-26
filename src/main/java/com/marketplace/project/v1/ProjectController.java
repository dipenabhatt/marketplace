package com.marketplace.project.v1;

import com.marketplace.project.v1.data.Project;
import com.marketplace.project.v1.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dipen.bhatt on 3/25/18.
 */

@RestController
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(path = "/v1/projects", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createProject(@RequestBody Project project){
        Long id = projectService.createProject(project);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }


    @RequestMapping(path = "/v1/projects/{projectId}", method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createProject(@PathVariable String projectId){
        Project project = projectService.findProjectById(Long.parseLong(projectId));
        return new ResponseEntity(project, HttpStatus.OK);
    }


}
