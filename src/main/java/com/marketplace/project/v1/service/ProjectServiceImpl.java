package com.marketplace.project.v1.service;

import com.marketplace.bids.v1.BidService;
import com.marketplace.bids.v1.data.ProjectBid;
import com.marketplace.project.v1.Entity.ProjectEntity;
import com.marketplace.project.v1.data.Project;
import com.marketplace.project.v1.ex.ProjectCreateException;
import com.marketplace.project.v1.repository.ProjectRepository;
import common.ex.NotFoundException;
import common.ex.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by dipen.bhatt on 3/25/18.
 */

@Service
public class ProjectServiceImpl implements ProjectService{

    ProjectRepository projectRepository;

    BidService bidService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, BidService bidService) {
        this.projectRepository = projectRepository;
        this.bidService = bidService;
    }

    @Override
    public Project findProjectById(Long id) throws NotFoundException{
        try {
            Optional<ProjectEntity> oProjectEntity = projectRepository.findById(id);
            if(!oProjectEntity.isPresent()){
                throw new NotFoundException("Project for Id "+id+" Not Found");
            }
            ProjectEntity projectEntity = oProjectEntity.get();
            Project project = new Project(projectEntity.getName(),projectEntity.getDescription(),projectEntity.getMaxBudgetAmount(),projectEntity.getBidingEndTime(),null);
            ProjectBid minBid = bidService.findMinBuyerBidByProjectId(projectEntity.getId().toString());
            project.setMinBid(minBid);
            return project;
        }catch (Exception e){
            e.printStackTrace();
            throw new NotFoundException("Project for Id "+id+" Not Found");
        }
    }


    @Override
    public Long createProject(Project project) throws ProjectCreateException,ValidationException{
        Long projectId = null;

        validateRequest(project);
        try{
            ProjectEntity projectEntity = new ProjectEntity(project.getName(),project.getDescription(),project.getMaxBudgetAmount(),project.getBidingEndTime());
            projectEntity = projectRepository.save(projectEntity);
            projectId = projectEntity.getId();
        }catch (Exception e){
            throw new ProjectCreateException(e);
        }
        return projectId;
    }

    private void validateRequest(Project project){
        if(null == project){
            throw new ValidationException("Project cannot be Null");
        }

        if(StringUtils.isEmpty(project.getName())){
            throw new ValidationException("Project Name cannot be Null");
        }

        if(ObjectUtils.isEmpty(project.getMaxBudgetAmount())){
            throw new ValidationException("Project Max budget Amount cannot be Null");
        }

        if(ObjectUtils.isEmpty(project.getBidingEndTime())){
            throw new ValidationException("Bid ending Time cannot cannot be Null");
        }

    }

}
