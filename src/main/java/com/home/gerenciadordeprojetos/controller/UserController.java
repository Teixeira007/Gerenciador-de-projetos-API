package com.home.gerenciadordeprojetos.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.home.gerenciadordeprojetos.exception.UserNotFoundException;
import com.home.gerenciadordeprojetos.model.User;
import com.home.gerenciadordeprojetos.repositories.UserRepository;
import com.home.gerenciadordeprojetos.service.UserService;

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
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private UserService userService;

    @GetMapping
    public List<User> listUser(){
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<User> listUser(@PathVariable Long idUser){
        return userRepository.findById(idUser)
        .map(user -> ResponseEntity.ok().body(user))
        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<User> updateUser(@Valid @PathVariable Long idUser, @RequestBody User user){

        Optional<User> user1 = userRepository.findById(idUser);
        if(user1.isPresent()){
            user.setId(user1.get().getId());
            userService.save(user);
            return ResponseEntity.ok().body(user);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<User> deleteUser(@PathVariable Long idUser){
        
        if(!userRepository.existsById(idUser)){
            return ResponseEntity.notFound().build();
        }

        userService.delete(idUser);
        return ResponseEntity.noContent().build();
    }

   
    
}
