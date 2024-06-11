package org.ej31.kcs5thjpa.services;

import org.ej31.kcs5thjpa.models.User;
import org.ej31.kcs5thjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(String name, String email) {
        return userRepository.findByEmail(email).orElseGet(
                () -> new User(name, email)
        );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User updateUser(Long id, String name, String email) {
        return userRepository.findById(id).map(user -> {
            user.setName(name);
            user.setEmail(email);
            return userRepository.save(user);
        }).orElse(null);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
