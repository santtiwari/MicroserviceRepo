package com.lcwd.rating.services.impl;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repositories.RatingRepository;
import com.lcwd.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingrepo;

    @Override
    public Rating createRating(Rating rating) {

        String ratingId= UUID.randomUUID().toString();
        rating.setRatingId(ratingId);

        return ratingrepo.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {

        return ratingrepo.findAll() ;
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {

        return ratingrepo.findByuserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {

        return ratingrepo.findByhotelId(hotelId);
    }
}
