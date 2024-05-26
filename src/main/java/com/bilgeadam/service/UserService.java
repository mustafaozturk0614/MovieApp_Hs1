package com.bilgeadam.service;

import com.bilgeadam.entity.User;
import com.bilgeadam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private  final UserRepository userRepository;

    public User register(String name, String surName, String password, String email) {
      User user=User.builder()
        .name(name)
        .surName(surName)
        .password(password)
        .email(email)
        .build();

    return userRepository.save(user);
    }

    public User findByEmail(String email){
        Optional<User> user= userRepository.findOptionalByEmail(email);
        if (user.isEmpty()){
            throw  new RuntimeException("Kullanıcı bulunamadı");
        }
        return user.get();
    }

    public Boolean login(String email, String password) {

       return userRepository.existsByEmailAndPassword(email, password);
    }

    public List<User> findAllByOrderByName(){
        return userRepository.findAllByOrderByName();
    }

    public List<User> findAllByNameContainsIgnoreCase(String value){
        return userRepository.findAllByNameContainsIgnoreCase(value);
    }
    public List<User> findAllByEmailContainsIgnoreCase(String value){
        return userRepository.findAllByEmailContainsIgnoreCase(value);
    }
}
