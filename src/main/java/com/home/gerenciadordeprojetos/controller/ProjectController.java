package com.home.gerenciadordeprojetos.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.home.gerenciadordeprojetos.model.Project;
import com.home.gerenciadordeprojetos.model.User;
import com.home.gerenciadordeprojetos.repositories.ProjectRepository;
import com.home.gerenciadordeprojetos.repositories.UserRepository;
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
    public ResponseEntity<Project> addProject(@Valid @RequestBody Project project){
        
        projectService.save(project);

        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @PutMapping("/{idProject}")
    public ResponseEntity<Project> update(@Valid @PathVariable Long idProject,
             @RequestBody Project project){

        Optional<Project> project1 = projectRepository.findById(idProject);

        if(project1.isPresent()){
            project.setId(idProject);
            projectService.save(project);
            return ResponseEntity.ok().body(project);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{idProject}")
    public ResponseEntity<Void> delete(@PathVariable Long idProject){
        Optional<Project> project1 = projectRepository.findById(idProject);

        if(project1.isPresent()) {
            projectRepository.deleteById(idProject);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
        

    }
    
    @GetMapping("/user/{idUser}")
    public List<Project> listProjectByUser(@PathVariable Long idUser){
        return projectRepository.findAll().stream()
        .filter(project -> project.getUser().getId().equals(idUser)).collect(Collectors.toList());
    }  
    
    @GetMapping("/inicializar/{idProject}")
    public ResponseEntity<Project> inicializar(@PathVariable Long idProject){
        Optional<Project> project = projectRepository.findById(idProject);


        if(project.isPresent()) {
            project.get().initProject();
            projectService.save(project.get());
            return ResponseEntity.ok().body(project.get());
        }else{
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping("/finalizar/{idProject}")
    public ResponseEntity<Project> finalizar(@PathVariable Long idProject){
        if(!projectRepository.existsById(idProject)){
            return ResponseEntity.notFound().build(); 
        }else{
            Project project = projectRepository.findById(idProject).get();
           
            project.finalProject();
            projectService.save(project);
            return ResponseEntity.ok().body(project);
        }

    
    }
}
