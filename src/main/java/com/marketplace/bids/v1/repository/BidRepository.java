package com.marketplace.bids.v1.repository;

import com.marketplace.bids.v1.data.ProjectBid;
import common.ex.NotFoundException;

/**
 * Created by dipen.bhatt on 3/25/18.
 */
public interface BidRepository {

    void addProjectBid(Long projectId,ProjectBid projectBid);
    ProjectBid findMinBuyerBidByProjectId(Long projectId) throws NotFoundException;
    Double findMinBidByProjectId(Long projectId) throws NotFoundException;

}
