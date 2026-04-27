package com.lcwd.user.service.controllers;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserControllers {


   @Autowired

   private UserServices userServices;
    //saveuser

    @PostMapping
    public ResponseEntity<User> saveUsers(@RequestBody User user){

        User user1= userServices.saveUser(user);
     return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }


    //get single user

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable  String userId){

        User user = userServices.getUserById(userId);

        return ResponseEntity.ok(user);
    }



    //get all user
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser= userServices.getAllUser();

        System.out.println("allUser "+allUser);

        return ResponseEntity.ok(allUser);
    }

}
