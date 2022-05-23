package com.home.gerenciadordeprojetos.repositories;

import com.home.gerenciadordeprojetos.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
