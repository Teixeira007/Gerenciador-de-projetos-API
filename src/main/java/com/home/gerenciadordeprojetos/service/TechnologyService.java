package com.home.gerenciadordeprojetos.service;

import javax.transaction.Transactional;

import com.home.gerenciadordeprojetos.exception.NegocioException;
import com.home.gerenciadordeprojetos.model.Project;
import com.home.gerenciadordeprojetos.model.Technology;
import com.home.gerenciadordeprojetos.repositories.ProjectRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TechnologyService {

    ProjectRepository projectRepository;
    
    @Transactional
    public Technology addTechnology(Long idProject, String nameTechnology){
        Project project = projectRepository.findById(idProject)
                .orElseThrow(() -> new NegocioException("Projeto não encontrado"));

        return project.addTechnologies(nameTechnology);
        
    }

}
