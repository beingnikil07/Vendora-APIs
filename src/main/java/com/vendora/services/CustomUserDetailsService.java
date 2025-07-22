package com.vendora.services;

import com.vendora.models.AuthUsers;
import com.vendora.repository.AuthUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthUserRepository authUserRepository;

    private final BCryptPasswordEncoder  bCryptPasswordEncoder=new BCryptPasswordEncoder();

    //logger
    private static final Logger logger= LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user details for username: {}", username);
       AuthUsers user = authUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        logger.info("User [{}] found with role: {}", user.getUsername(), user.getRole());
       return new User(
               user.getUsername(),
               user.getPassword(),
               List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole()))
       );
    }

    //To Login a user
    public boolean authenticateUser(String username, String password) {
        logger.info("Authenticating user: {}", username);
        Optional<AuthUsers> user =authUserRepository.findByUsername(username);

        if(user.isPresent()){
            AuthUsers authUser = user.get();
            logger.info("Authentication successful for user: {}", username);
            return  bCryptPasswordEncoder.matches(password,authUser.getPassword());
        }else{
            logger.warn("Authentication failed - username not found: {}", username);
            return false;
        }
    }

}
