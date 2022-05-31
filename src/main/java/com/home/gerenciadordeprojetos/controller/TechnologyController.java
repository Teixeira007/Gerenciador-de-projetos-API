package com.home.gerenciadordeprojetos.controller;

import com.home.gerenciadordeprojetos.exception.NegocioException;
import com.home.gerenciadordeprojetos.model.Project;
import com.home.gerenciadordeprojetos.model.Technology;
import com.home.gerenciadordeprojetos.repositories.ProjectRepository;
import com.home.gerenciadordeprojetos.service.TechnologyService;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/projects/{idProject}/technologies")
public class TechnologyController {
    
    ProjectRepository projectRepository;
    TechnologyService technologyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Technology> addTechnology(@Valid @RequestBody Technology technology, 
            @PathVariable Long idProject){
        
        Technology technologyAdd = technologyService.addTechnology(idProject, technology.getName());
        
        return ResponseEntity.ok().body(technologyAdd);
    }

    @GetMapping
    public List<Technology> listTechnologies(@PathVariable Long idProject){
        Project project = projectRepository.findById(idProject)
        .orElseThrow(() -> new NegocioException("Projeto não encontrado"));

        // List<String> technologies = new ArrayList<>();
        // for(Technology technology: project.getTechnologies()){
        //     technologies.add(technology.getName());
        // }
        return project.getTechnologies();
    }
}
