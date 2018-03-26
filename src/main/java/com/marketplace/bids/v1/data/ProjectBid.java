package com.marketplace.bids.v1.data;

/**
 * Created by dipen.bhatt on 3/25/18.
 */
public class ProjectBid implements Comparable<ProjectBid>{

    private String bidAmount;
    private String buyerId;

    public String getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(String bidAmount) {
        this.bidAmount = bidAmount;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public int compareTo(ProjectBid o) {
        if(bidAmount == null || null == o.getBidAmount()){
            return -1;
        }
        return Double.valueOf(this.getBidAmount()).compareTo(Double.valueOf(o.getBidAmount()));
    }

    @Override
    public String toString() {
        return "ProjectBid{" +
                "buyerId=" + buyerId +
                ", bidAmount=" + bidAmount +
                '}';
    }
}
