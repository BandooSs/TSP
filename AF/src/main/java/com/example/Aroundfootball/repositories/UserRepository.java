package com.example.Aroundfootball.repositories;

import com.example.Aroundfootball.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

}
