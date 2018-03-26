package com.marketplace.project.v1.repository;

import com.marketplace.project.v1.Entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dipen.bhatt on 3/25/18.
 */
@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity,Long>{
}
