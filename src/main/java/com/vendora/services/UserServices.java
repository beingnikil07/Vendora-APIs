package com.vendora.services;

import com.vendora.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServices {
    public User createUser(User user);
    public ResponseEntity<User> updateUser(Integer user_id, User user);
    public ResponseEntity<String> deleteUser(Integer user_id);
    public ResponseEntity<User> getUserById(Integer user_id);
    public ResponseEntity<List<User>> getAllUsers();
}
