package com.lcwd.user.service.external.services;

import com.lcwd.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(name = "RATINGSERVICE")
public interface RatingService {



    //post
    @PostMapping("/ratings")
    public ResponseEntity<Rating> cretaeRating(Rating values);


    //put
    @PutMapping("ratings/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId, Rating rating);

    //delete
    @DeleteMapping("ratings/{ratingId}")

    public ResponseEntity<Rating> deleteRating(@PathVariable String ratingId);

}
