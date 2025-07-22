package com.vendora.services;

import com.vendora.models.User;
import com.vendora.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices{
        //Injecting bean
        private final UserRepository userRepository;
        public UserServicesImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        //Logger
        private static final Logger logger= LoggerFactory.getLogger(UserServicesImpl.class);


        //To create user
        @Override
        public User createUser(User user){
                logger.info("Attempting to create user");
                if(user!=null) {
                        User u = userRepository.save(user);
                        logger.info("User created Successfully");
                        return u;
                }else{
                        logger.info("User not found");
                        return null;
                }
        }

        @Override
        public ResponseEntity<User> updateUser(Integer user_id, User user) {
               logger.info("Attempting to update user");
                //find user
                User existedUser=userRepository.findById(user_id).orElse(null);

                if(existedUser==null){
                        logger.info("User not found");
                        return ResponseEntity.notFound().build();
                }

                //update only fields that are coming in request
                if(user.getName()!=null){
                        existedUser.setName(user.getName());
                }

                if(user.getAddress()!=null){
                        existedUser.setAddress(user.getAddress());

                }
                if(user.getEmail()!=null){
                        existedUser.setEmail(user.getEmail());
                }
                if (user.getPassword()!=null){
                        existedUser.setPassword(user.getPassword());
                }
                if(user.getPhone_no()!=null) {
                        existedUser.setPhone_no(user.getPhone_no());
                }
                //save and return the existed user
                User updatedUser = userRepository.save(existedUser);
                logger.info("User Updated Successfully");
                return ResponseEntity.ok(updatedUser);
        }


        //To delete user --Admin accessible
        public ResponseEntity<String>   deleteUser(Integer user_id){
                logger.info("Attempting to delete user with Id:{}", user_id);
                if (!userRepository.existsById(user_id)){
                        logger.info("User with Id:{} not found", user_id);
                     return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
                }

                userRepository.deleteById(user_id);
                logger.info("User deleted Successfully");
                return ResponseEntity.ok("User deleted successfully");
        }

        //Get one user
        @Override
        public ResponseEntity<User> getUserById(Integer user_id) {
                logger.info("Attempting to get User with Id:{}", user_id);
                if(!userRepository.existsById(user_id)){
                        logger.info("User with Id:{} not found", user_id);
                    return ResponseEntity.notFound().build();
                }
                logger.info("User with Id:{} found", user_id);
                return  ResponseEntity.ok(userRepository.findById(user_id).get());
        }

        @Override
        public Page<User> getAllUsers(Pageable pageable) {
                logger.info("Attempting to get all users");
                return userRepository.findAll(pageable);
        }

}
