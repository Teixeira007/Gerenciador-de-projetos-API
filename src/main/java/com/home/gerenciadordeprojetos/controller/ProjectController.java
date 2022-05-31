package com.home.gerenciadordeprojetos.controller;

import java.util.List;

import javax.validation.Valid;

import com.home.gerenciadordeprojetos.model.Project;
import com.home.gerenciadordeprojetos.repositories.ProjectRepository;
import com.home.gerenciadordeprojetos.service.ProjectService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectRepository projectRepository;
    private ProjectService projectService;

    // Listar
    @GetMapping
    public List<Project> listProject(){
        return projectRepository.findAll();
    }

    @GetMapping("/{idProject}")
    public ResponseEntity<Project> listProject(@PathVariable Long idProject){
        return projectRepository.findById(idProject).
        map(project -> ResponseEntity.ok().body(project))
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Project> addProject(@Valid @RequestBody Project project){
        project.initProject();
        
        projectService.save(project);


        return ResponseEntity.ok().body(project);
    }

    @PutMapping("/{idProject}")
    public ResponseEntity<Project> update(@Valid @PathVariable Long idProject,
             @RequestBody Project project){
            
        if(!projectRepository.existsById(idProject)){
            return ResponseEntity.notFound().build();
        }

        project.setId(idProject);
        projectService.save(project);
        return ResponseEntity.ok().body(project);
    }

    @DeleteMapping("/{idProject}")
    public ResponseEntity<Project> delete(@PathVariable Long idProject){
        if(!projectRepository.existsById(idProject)){
            return ResponseEntity.notFound().build();
        }
        
        projectService.delete(idProject);
        return ResponseEntity.noContent().build();
    }
    
}
