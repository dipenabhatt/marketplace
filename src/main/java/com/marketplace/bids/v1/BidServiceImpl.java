package com.marketplace.bids.v1;

import com.marketplace.bids.v1.data.ProjectBid;
import com.marketplace.bids.v1.repository.BidRepository;
import com.marketplace.project.v1.Entity.ProjectEntity;
import com.marketplace.project.v1.repository.ProjectRepository;
import common.ex.EntityCreateException;
import common.ex.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


/**
 * Created by dipen.bhatt on 3/25/18.
 */

@Service
public class BidServiceImpl implements BidService{

    ProjectRepository projectRepository;

    BidRepository bidRepository;

    @Autowired
    public BidServiceImpl(ProjectRepository projectRepository, BidRepository bidRepository) {
        this.projectRepository = projectRepository;
        this.bidRepository = bidRepository;
    }

    @Override
    public ProjectBid findMinBuyerBidByProjectId(String projectId){
        Long lProjectId = Long.valueOf(projectId);
        return bidRepository.findMinBuyerBidByProjectId(lProjectId);
    }

    @Override
    public Double findMinBidByProjectId(String projectId){
        Long lProjectId = Long.valueOf(projectId);
        return bidRepository.findMinBidByProjectId(lProjectId);
    }


    @Override
    public void createBid(String projectId, ProjectBid bid){
        try {
            validateRequest(projectId, bid);
            bidRepository.addProjectBid(Long.valueOf(projectId), bid);
        }catch (ValidationException e) {
            throw e;
        }catch (Exception e){
            e.printStackTrace();
            throw new EntityCreateException("Cannot Create Project bid.");
        }
    }


    private void validateRequest(String projectId,ProjectBid bid){
        Long lProjectId = null;
        Double dBidAmount = null;

        try {
            lProjectId = Long.parseLong(projectId);
            dBidAmount = Double.parseDouble(bid.getBidAmount());
        }catch (Exception e){
            throw new ValidationException("Invalid Parameter.");
        }

        Optional<ProjectEntity> oProject = projectRepository.findById(lProjectId);
        if(!oProject.isPresent()){
            throw new ValidationException("Project not Found.");
        }
        ProjectEntity project = oProject.get();
        if(project.getMaxBudgetAmount() < dBidAmount){
            throw new ValidationException("Bid Amount acceded max budget.");
        }

        if(project.getBidingEndTime().isBefore(LocalDateTime.now())){
            throw new ValidationException("Bid Amount acceded max budget.");
        }

    }


}
