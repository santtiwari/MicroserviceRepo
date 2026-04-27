package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    //create service

    Rating createRating(Rating rating);


    //get all services

    List<Rating> getAllRating();

    //get service by userid

    List<Rating> getRatingByUserId(String userId);


    //get all by hotel

    List<Rating> getRatingByHotelId(String hotelId);
}
