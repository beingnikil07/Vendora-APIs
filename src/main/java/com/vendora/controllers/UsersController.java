package com.vendora.controllers;
import com.vendora.models.User;
import com.vendora.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name ="User controller",description = "Manage users ")
public class UsersController {

    //Bean injection
    private final UserServices userServices;
    public UsersController(UserServices userServices) {
        this.userServices = userServices;
    }

    //Logger
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);


    @Operation(summary = "Create a user ",description = "You can create a user by using this api")
    @PostMapping(value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public User createUser(@Valid @RequestBody User user){
       logger.info("Creating a user...");
       User user1=userServices.createUser(user);
        logger.info("Successfully created a user");
        return user1;
    }

    @Operation(summary = "Update a user", description = "Update an existing user's details by ID")
    @PatchMapping(value = "/update/{user_id}",
                  consumes = "application/json",
                  produces = "application/json"
    )
    public ResponseEntity<User> updateUserDetails(@Valid @PathVariable Integer user_id, @RequestBody User user){
       logger.info("Update user details by ID");
       ResponseEntity<User> response=userServices.updateUser(user_id,user);
        logger.info("Successfully Updated user details by ID");
        return response;
    }

    @Operation(summary = "Delete a user", description = "Delete user by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        logger.info("Deleting user with ID:{}",id);
        ResponseEntity<String> response=userServices.deleteUser(id);
        logger.info("Successfully deleted user with ID:{}",id);
        return response;
    }

    //To get a Single User Details
    @Operation(summary = "Get single user", description = "Fetch a single user by ID")
    @GetMapping(value = "/getuser/{id}",
                produces = "application/json"
    )
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        logger.info("Fetching user by ID:{}",id);
        ResponseEntity<User> response= userServices.getUserById(id);
        logger.info("Successfully fetched user by ID:{}",id);
        return response;
    }

    //To get all Users
    @Operation(summary = "Get all users", description = "Fetch all users in the system")
    @GetMapping(value = "/getusers",
                produces = "application/json"
    )
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam(defaultValue ="0") int page,
                                                  @RequestParam(defaultValue = "5") int size
                                                  ){
        logger.info("Fetching all users in the system");
        Pageable pageable = PageRequest.of(page, size);
        Page<User> Userpage=userServices.getAllUsers(pageable);
        logger.info("Successfully fetched all users in the system");
        return ResponseEntity.ok(Userpage);
    }

}
