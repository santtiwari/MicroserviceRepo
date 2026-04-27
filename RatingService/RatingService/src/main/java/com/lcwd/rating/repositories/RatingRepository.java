package com.lcwd.rating.repositories;

import com.lcwd.rating.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, String> {

    //user defined method

    List<Rating> findByuserId(String userId);

    List<Rating> findByhotelId(String hotelId);
}
