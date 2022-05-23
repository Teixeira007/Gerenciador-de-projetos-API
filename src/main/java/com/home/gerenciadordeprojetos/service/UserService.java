package com.home.gerenciadordeprojetos.service;

import javax.transaction.Transactional;

import com.home.gerenciadordeprojetos.exception.NegocioException;
import com.home.gerenciadordeprojetos.model.User;
import com.home.gerenciadordeprojetos.repositories.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {
    
    private UserRepository userRepository;

    @Transactional
    public void save(User user){
        boolean usedEmail = userRepository.findByEmail(user.getEmail())
        .stream()
        .anyMatch(userExistente -> !userExistente.equals(user));

        if(usedEmail){
            throw new NegocioException("Esse email já está sendo usado");
        }
        userRepository.save(user);
    }

    @Transactional
    public void delete(Long idUser){
        userRepository.deleteById(idUser);
    }
}
