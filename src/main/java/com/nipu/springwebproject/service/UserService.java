package com.nipu.springwebproject.service;

import com.nipu.springwebproject.model.User;
import com.nipu.springwebproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findByAge(Long age){
        return userRepository.findByAge(age);
    }
    public User save(User user){
        return userRepository.save(user);
    }
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
    public void delete(User user){
        userRepository.delete(user);
    }
}
