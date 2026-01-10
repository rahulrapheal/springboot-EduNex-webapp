package com.luminar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.luminar.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Custom finder method
    User findByEmail(String email);
}
