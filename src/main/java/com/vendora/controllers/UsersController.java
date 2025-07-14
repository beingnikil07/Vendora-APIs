package com.vendora.controllers;
import com.vendora.models.User;
import com.vendora.services.UserServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
