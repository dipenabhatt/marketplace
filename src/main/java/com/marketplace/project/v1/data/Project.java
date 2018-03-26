package com.marketplace.project.v1.data;

import com.marketplace.bids.v1.data.ProjectBid;

import java.time.LocalDateTime;

/**
 * Created by dipen.bhatt on 3/25/18.
 */
public class Project {

    private String name;
    private String description;
    private Double maxBudgetAmount;
    private LocalDateTime bidingEndTime;
    private ProjectBid minBid;


    public Project(String name, String description, Double maxBudgetAmount, LocalDateTime bidingEndTime, ProjectBid minBid) {
        this.name = name;
        this.description = description;
        this.maxBudgetAmount = maxBudgetAmount;
        this.bidingEndTime = bidingEndTime;
        this.minBid = minBid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMaxBudgetAmount() {
        return maxBudgetAmount;
    }

    public void setMaxBudgetAmount(Double maxBudgetAmount) {
        this.maxBudgetAmount = maxBudgetAmount;
    }

    public LocalDateTime getBidingEndTime() {
        return bidingEndTime;
    }

    public void setBidingEndTime(LocalDateTime bidingEndTime) {
        this.bidingEndTime = bidingEndTime;
    }

    public ProjectBid getMinBid() {
        return minBid;
    }

    public void setMinBid(ProjectBid minBid) {
        this.minBid = minBid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return name.equals(project.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", maxBudgetAmount=" + maxBudgetAmount +
                ", bidingEndTime=" + bidingEndTime +
                '}';
    }
}
