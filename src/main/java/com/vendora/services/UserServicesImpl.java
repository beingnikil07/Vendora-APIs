package com.vendora.services;

import com.vendora.models.User;
import com.vendora.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices{
        //Injecting bean
        private final UserRepository userRepository;
        public UserServicesImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public User createUser(User user){
                User u=userRepository.save(user);
                return u;
        }


}
