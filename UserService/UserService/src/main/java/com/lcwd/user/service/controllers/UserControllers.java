package com.lcwd.user.service.controllers;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserServices;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserControllers {

    private static final Logger logger = LoggerFactory.getLogger(UserControllers.class);


   @Autowired

   private UserServices userServices;
    //saveuser

    @PostMapping
    public ResponseEntity<User> saveUsers(@RequestBody User user){

        User user1= userServices.saveUser(user);
     return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }


    //get single user
    int retrycount=1;

    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable  String userId){
         logger.info("get single user handler: userController");

         logger.info("Retry count: {}", retrycount);
        retrycount++;

        User user = userServices.getUserById(userId);

        return ResponseEntity.ok(user);
    }

    //creating fall back method for circuitbreaker


    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){

        //logger.info("Fallback is executed because ervice is down", ex.getMessage());

        User user = User.builder()
                .email("santprasad.8989@gmail.com")
                .name("sanju tiwari")
                .about("This user is created dummy because some service is down")
                .userId("1236547")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    //get all user
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser= userServices.getAllUser();

        System.out.println("allUser "+allUser);

        return ResponseEntity.ok(allUser);
    }

}
