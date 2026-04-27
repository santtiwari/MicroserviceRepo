package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserServices {

    //save user details

    User saveUser(User user);

    //get all user

    List<User> getAllUser();

    //get user byId

    User getUserById(String userId);




}
