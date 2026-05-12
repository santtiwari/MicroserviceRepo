package com.lcwd.user.service.services.Impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepositories;
import com.lcwd.user.service.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepositories userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;


    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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

        //get user from database with the help of user repository

       User user= userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User given id is not found on server !! : "+userId));

       //fetch rating of the above user from rating service
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingOfUser);

         List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {

            //api call to hotel service to get the hotel


            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);

            //Hotel hotel = forEntity.getBody();

            //fiegnclient
            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            //logger.info("Response status code: {}",forEntity.getStatusCode());

            //set the hotel to rating

            rating.setHotel(hotel);

            //return the rating

            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

       return user;

    }
}
