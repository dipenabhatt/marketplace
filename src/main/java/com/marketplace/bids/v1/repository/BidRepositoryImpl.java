package com.marketplace.bids.v1.repository;

import com.marketplace.bids.v1.data.ProjectBid;
import common.ex.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dipen.bhatt on 3/25/18.
 */
@Repository
public class BidRepositoryImpl implements BidRepository{

    private ConcurrentHashMap<Long,PriorityQueue<ProjectBid>> projectBidMap = new ConcurrentHashMap<>();

    @Override
    public void addProjectBid(Long projectId,ProjectBid projectBid){
        PriorityQueue<ProjectBid> projectBids = projectBidMap.get(projectId);
        if(CollectionUtils.isEmpty(projectBids)){
            projectBids = new PriorityQueue<>();
        }

        synchronized (projectBids) {
            projectBids.offer(projectBid);
            projectBidMap.put(projectId,projectBids);
        }
    }

    @Override
    public ProjectBid findMinBuyerBidByProjectId(Long projectId) throws NotFoundException{
        PriorityQueue<ProjectBid> projectBids = projectBidMap.get(projectId);
        if(projectBids == null){
            return null;
        }
        ProjectBid projectBid = projectBids.peek();
        return projectBid;
    }

    @Override
    public Double findMinBidByProjectId(Long projectId) throws NotFoundException{
        Double minBid = null;
        PriorityQueue<ProjectBid> projectBids = projectBidMap.get(projectId);
        ProjectBid projectBid = projectBids.peek();
        if(projectBid != null){
            minBid = Double.valueOf(projectBid.getBidAmount());
        }
        return minBid;
    }

}
