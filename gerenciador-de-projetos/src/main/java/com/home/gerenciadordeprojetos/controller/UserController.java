package com.home.gerenciadordeprojetos.controller;

import java.util.List;

import com.home.gerenciadordeprojetos.model.User;
import com.home.gerenciadordeprojetos.repositories.UserRepository;

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

    @GetMapping
    public List<User> listUser(){
        return userRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user){
        userRepository.save(user);
        return user;
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<User> listUser(@PathVariable Long idUser){
        return userRepository.findById(idUser)
        .map(user -> ResponseEntity.ok().body(user))
        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<User> updateUser(@PathVariable Long idUser, @RequestBody User user){
        if(!userRepository.existsById(idUser)){
            return ResponseEntity.notFound().build();
        }

        user.setId(idUser);
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<User> deleteUser(@PathVariable Long idUser){
        
        if(!userRepository.existsById(idUser)){
            return ResponseEntity.notFound().build();
        }

        
        userRepository.deleteById(idUser);
        return ResponseEntity.noContent().build();
        
    }

    
}
