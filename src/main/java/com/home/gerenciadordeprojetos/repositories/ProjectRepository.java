package com.home.gerenciadordeprojetos.repositories;

import com.home.gerenciadordeprojetos.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>{
    
}
