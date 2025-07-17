package com.vendora.models;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Schema(name = "User", description = "Represents a registered user in the application")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the user", example = "101")
    private int user_id;

    @NotNull(message = "user name is required")
    @Size(min = 3, max = 50,message ="user name length must be between 3 to 50")
    @Schema(description = "Full name of the user", example = "Hritik Rana", required = true)
    private String name;

    @Column(unique = true)
    @NotNull(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    @Schema(description = "Email address of the user", example = "nikhilkumar@example.com", required = true)
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Schema(description = "Password for user login", example = "securePass123", required = true)
    private String password;

    @Schema(description = "Address of the user", example = "123 Street Name, City")
    private String address;

    @Schema(description = "Timestamp when the user was registered", example = "2025-07-17T09:30:00")
    private LocalDateTime created_at;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    @Schema(description = "User's phone number", example = "9876543210")
    private String phone_no;


    //so that it automatically filled when a user is created ,no need to provide it at the time of api calling
    @PrePersist
    public void onCreate(){
        this.created_at=LocalDateTime.now();
    }



    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}
