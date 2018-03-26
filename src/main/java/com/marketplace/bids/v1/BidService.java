package com.marketplace.bids.v1;

import com.marketplace.bids.v1.data.ProjectBid;

/**
 * Created by dipen.bhatt on 3/25/18.
 */
public interface BidService {

    ProjectBid findMinBuyerBidByProjectId(String projectId);
    Double findMinBidByProjectId(String projectId);
    void createBid(String projectId, ProjectBid bid);

}
