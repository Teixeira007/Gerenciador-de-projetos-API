package com.home.gerenciadordeprojetos;

import com.home.gerenciadordeprojetos.controller.ProjectController;
import com.home.gerenciadordeprojetos.model.Project;
import com.home.gerenciadordeprojetos.model.Technology;
import com.home.gerenciadordeprojetos.model.User;
import com.home.gerenciadordeprojetos.repositories.ProjectRepository;
import com.home.gerenciadordeprojetos.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class ProjectTest {

    @Mock
    ProjectRepository projectRepository;

    @Mock
    ProjectService projectService;

    @InjectMocks
    ProjectController projectController;

    @Test
    public void testListProject() {
        List<Project> projectList = Arrays.asList(
                createProject(1L, "Projeto Restaurante"),
                createProject(2L, "Projeto Catalogo")
        );
        when(projectRepository.findAll()).thenReturn(projectList);

        List<Project> result = projectController.listProject();

        assertEquals(2, result.size());
        assertEquals("Projeto Restaurante", result.get(0).getName());
        assertEquals("Joao Silva", result.get(1).getUser().getName());
        assertEquals(2, result.get(1).getTechnologies().size());
    }

    @Test
    public void testAddProject() {
        Project project = createProject(1L, "Projeto de Loja");
        when(projectService.save(project)).thenReturn(project);

        ResponseEntity<Project> responseEntity = projectController.addProject(project);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Projeto de Loja", responseEntity.getBody().getName());
        assertEquals("Joao Silva", responseEntity.getBody().getUser().getName());
    }

    @Test
    public void testUpdateProject() {
        Project projectExistent = createProject(1L, "Projeto de Filmes");

        when(projectRepository.findById(projectExistent.getId())).thenReturn(Optional.of(projectExistent));
        when(projectRepository.findById(2L)).thenReturn(Optional.empty());


        Project newProject = createProject(1L, "Projeto Streames de Filmes");

        ResponseEntity<Project> responseEntity = projectController.update(1L, newProject);
        ResponseEntity<Project> responseEntity2 = projectController.update(2L, newProject);

//        Testes de Sucesso
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newProject, responseEntity.getBody());
        assertEquals("Projeto Streames de Filmes", responseEntity.getBody().getName());

//        Testes de Falha
        assertEquals(HttpStatus.NOT_FOUND, responseEntity2.getStatusCode());
        assertNull(responseEntity2.getBody());
    }

    @Test
    public void testDeleteProject(){
        Project projectExistent = createProject(1L, "Projeto de Filmes");

        when(projectRepository.findById(projectExistent.getId())).thenReturn(Optional.of(projectExistent));
        when(projectRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Void> responseEntity = projectController.delete(1L);
        ResponseEntity<Void> responseEntity2 = projectController.delete(2L);

//        Testes de Sucesso
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(projectRepository).deleteById(1L);

//        Testes de Falha
        assertEquals(HttpStatus.NOT_FOUND, responseEntity2.getStatusCode());

    }

    @Test
    public void testInitTimeProject(){
        User user = new User(1L, "Joao Silva", "joao@example.com");
        Project projectExistent = new Project(1L, "Projeto Estacionamento", user);

        when(projectRepository.findById(projectExistent.getId())).thenReturn(Optional.of(projectExistent));
        when(projectRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Project> responseEntity = projectController.inicializar(1L);
        ResponseEntity<Project> responseEntity2 = projectController.inicializar(2L);

//        Testes de Sucesso
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getDateInit());

//        Testes de Falha
        assertEquals(HttpStatus.NOT_FOUND, responseEntity2.getStatusCode());
        assertNull(responseEntity2.getBody());
    }

    public Project createProject(Long id, String name){
        User user = new User(1L, "Joao Silva", "joao@example.com");
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java"));
        technologies.add((new Technology(2L, "Spring Boot")));
        OffsetDateTime dateTime = OffsetDateTime.now();
        OffsetDateTime dateTimeEnd = dateTime.plusHours(2);
        Project project = new Project(id, name, user, technologies, dateTime, dateTimeEnd);
        return project;
    }
}
