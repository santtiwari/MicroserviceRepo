package com.lcwd.hotel.controller;

import com.lcwd.hotel.entites.Hotel;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelservice;

    //save data

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){

     return ResponseEntity.status(HttpStatus.CREATED).body(hotelservice.create(hotel));
    }


    //get all data

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHoteles(){

        return ResponseEntity.ok(hotelservice.getAllHotel());
    }


    //get data by id

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){

        return ResponseEntity.status(HttpStatus.OK).body(hotelservice.getHotelById(hotelId));

    }
}
