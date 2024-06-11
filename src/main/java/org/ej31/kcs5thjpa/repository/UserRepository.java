package org.ej31.kcs5thjpa.repository;

import org.ej31.kcs5thjpa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
