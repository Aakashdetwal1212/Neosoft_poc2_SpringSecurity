package com.poc2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc2.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
