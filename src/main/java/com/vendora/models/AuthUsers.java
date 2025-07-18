package com.vendora.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

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
    private String username;

    @Schema(description = "Password is required for authentication and authorization")
    private String password;

    @Schema(description = "Role is required ")
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
