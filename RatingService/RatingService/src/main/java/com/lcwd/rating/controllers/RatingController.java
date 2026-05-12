package com.lcwd.rating.controllers;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingserv;

    //create rating

    @PostMapping
    public ResponseEntity<Rating> saveRtaing(@RequestBody Rating rating){

       return ResponseEntity.status(HttpStatus.CREATED).body(ratingserv.createRating(rating));

    }

    //get all

    @GetMapping
    public ResponseEntity<List<Rating>> getListofRating(){

        return ResponseEntity.ok(ratingserv.getAllRating());
    }

    //get all user

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){

        return ResponseEntity.ok(ratingserv.getRatingByUserId(userId));
    }


    //get all hotel

    @GetMapping("/hoteles/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){

        return ResponseEntity.ok(ratingserv.getRatingByHotelId(hotelId));
    }



}
