package com.nipu.springwebproject.repository;

import com.nipu.springwebproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findByAge(Long age);
}
