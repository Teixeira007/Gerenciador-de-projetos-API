package com.home.gerenciadordeprojetos;

import com.home.gerenciadordeprojetos.controller.UserController;
import com.home.gerenciadordeprojetos.model.User;
import com.home.gerenciadordeprojetos.repositories.UserRepository;
import com.home.gerenciadordeprojetos.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testListUser() {
        List<User> userList = Arrays.asList(
                new User(1L, "Joao Silva", "joao@example.com"),
                new User(2L, "Maria Souza", "maria@gmail.com")
        );
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userController.listUser();

        assertEquals(2, result.size());
        assertEquals("joao@example.com", result.get(0).getEmail());
        assertEquals("Maria Souza", result.get(1).getName());
    }

    @Test
    public void testAddUser() {
        User newUser = new User(null, "Bernado Oliveira", "bernad209@gmail.com");
        when(userService.save(newUser)).thenReturn(newUser);

        ResponseEntity<User> responseEntity = userController.addUser(newUser);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("bernad209@gmail.com", responseEntity.getBody().getEmail());
        assertEquals("Bernado Oliveira", responseEntity.getBody().getName());
    }

    @Test
    public void testListUserById() {
        User user = new User(1L, "João da Silva", "joao@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<User> responseEntity = userController.listUser(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("joao@example.com", responseEntity.getBody().getEmail());
        assertEquals("João da Silva", responseEntity.getBody().getName());
    }

    @Test
    public void testListUserByIdNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<User> responseEntity = userController.listUser(2L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testUpdateUser() {
        User existingUser = new User(1L, "joao@example.com", "João da Silva");

        when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        when(userRepository.findById(2L)).thenReturn(Optional.empty());


        User updatedUser = new User(1L, "joao.silva@example.com", "João Silva");

        ResponseEntity<User> responseEntity = userController.updateUser(1L, updatedUser);
        ResponseEntity<User> responseEntity2 = userController.updateUser(2L, updatedUser);

//        Testes de Sucesso
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedUser, responseEntity.getBody());

//        Testes de Falha
        assertEquals(HttpStatus.NOT_FOUND, responseEntity2.getStatusCode());
        assertNull(responseEntity2.getBody());
    }





}
