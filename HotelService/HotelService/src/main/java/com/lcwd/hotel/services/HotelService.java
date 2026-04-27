package com.lcwd.hotel.services;

import com.lcwd.hotel.entites.Hotel;

import java.util.List;

public interface HotelService {


    //save hotel data

    Hotel create(Hotel hotel);



    //get all hotel

    List<Hotel> getAllHotel();


    //get hotel by id

    Hotel getHotelById(String id);



}
