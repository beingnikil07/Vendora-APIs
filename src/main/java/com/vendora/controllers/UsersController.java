package com.vendora.controllers;
import com.vendora.models.User;
import com.vendora.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name ="User controller",description = "Manage users ")
public class UsersController {

    //Bean injection
    private final UserServices userServices;
    public UsersController(UserServices userServices) {
        this.userServices = userServices;
    }

    @Operation(summary = "Create a user ",description = "You can create a user by using this api")
    @PostMapping(value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public User createUser(@Valid @RequestBody User user){
        return userServices.createUser(user);
    }

    @Operation(summary = "Update a user", description = "Update an existing user's details by ID")
    @PatchMapping(value = "/update/{user_id}",
                  consumes = "application/json",
                  produces = "application/json"
    )
    public ResponseEntity<User> updateUserDetails(@Valid @PathVariable Integer user_id, @RequestBody User user){
        return userServices.updateUser(user_id,user);
    }

    @Operation(summary = "Delete a user", description = "Delete user by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        return userServices.deleteUser(id);
    }

    //To get a Single User Details
    @Operation(summary = "Get single user", description = "Fetch a single user by ID")
    @GetMapping(value = "/getuser/{id}",
                produces = "application/json"
    )
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        return userServices.getUserById(id);
    }

    //To get all Users
    @Operation(summary = "Get all users", description = "Fetch all users in the system")
    @GetMapping(value = "/getusers",
                produces = "application/json"
    )
    public ResponseEntity<List<User>> getAllUsers(){
        return userServices.getAllUsers();
    }

}
