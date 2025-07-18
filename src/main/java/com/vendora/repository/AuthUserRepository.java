package com.vendora.repository;

import com.vendora.models.AuthUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUsers,Long> {

    Optional<AuthUsers> findByUsername(String username);
}
