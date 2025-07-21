package com.vendora.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name ="auth_user")
@Schema(name ="Users Authentication",description = "user security manager")
public class AuthUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id is required")
    private Long id;

    @Column(unique = true)
    @Schema(description = "username is required for the authentication")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    @NotBlank(message="Username cannot be blank")
    private String username;

    @Schema(description = "Password is required for authentication and authorization")
    @Size(min = 6, max = 100, message = "Password must be at least 6 characters")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Schema(description = "Role is required ")
    @NotBlank(message = "Role cannot be blank")
    private String role;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
