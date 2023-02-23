package com.vc.kanbanProject.repository;

import com.vc.kanbanProject.domain.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends MongoRepository<Project,Integer> {
    Project findById(int project_id);
}
