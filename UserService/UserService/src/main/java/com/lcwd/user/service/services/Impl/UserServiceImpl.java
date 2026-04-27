package com.lcwd.user.service.services.Impl;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.repositories.UserRepositories;
import com.lcwd.user.service.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepositories userRepo;

    @Override
    public User saveUser(User user) {

       String randomUserId = UUID.randomUUID().toString();

       user.setUserId(randomUserId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {


        return userRepo.findAll();
    }

    @Override
    public User getUserById(String userId) {

       return userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User given id is not found on server !! : "+userId));

    }
}
