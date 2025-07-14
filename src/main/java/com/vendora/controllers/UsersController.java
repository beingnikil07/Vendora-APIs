package com.vendora.controllers;
import com.vendora.models.User;
import com.vendora.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    //Bean injection
    private final UserServices userServices;
    public UsersController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userServices.createUser(user);
    }

    @PatchMapping("/update/{user_id}")
    public ResponseEntity<User> updateUserDetails(@PathVariable Integer user_id, @RequestBody User user){
        return userServices.updateUser(user_id,user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        return userServices.deleteUser(id);
    }

    //To get a Single User Details
    @GetMapping("/getuser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        return userServices.getUserById(id);
    }

    //To get all Users
    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getAllUsers(){
        return userServices.getAllUsers();
    }

}
