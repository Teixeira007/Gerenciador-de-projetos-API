package com.home.gerenciadordeprojetos.service;

import com.home.gerenciadordeprojetos.model.Project;
import com.home.gerenciadordeprojetos.repositories.ProjectRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProjectService {
    
    private ProjectRepository projectRepository;

    public void save(Project project){
        projectRepository.save(project);
    }

    public void delete(Long idProject){
        projectRepository.deleteById(idProject);
    }
}
