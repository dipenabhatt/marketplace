package com.marketplace.project.v1.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by dipen.bhatt on 3/25/18.
 */

@Entity
@Table(name = "PROJECT")
public class ProjectEntity {

    @Id
    @Column
    @SequenceGenerator(name = "PROJECT_SEQ_GENERATOR", sequenceName = "PROJECT_SEQ",allocationSize=1)
    @GeneratedValue(generator = "PROJECT_SEQ_GENERATOR", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Double maxBudgetAmount;

    @Column
    private LocalDateTime bidingEndTime;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;


    public ProjectEntity() {
    }

    public ProjectEntity(String name, String description, Double maxBudgetAmount, LocalDateTime bidingEndTime) {
        this.name = name;
        this.description = description;
        this.maxBudgetAmount = maxBudgetAmount;
        this.bidingEndTime = bidingEndTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
