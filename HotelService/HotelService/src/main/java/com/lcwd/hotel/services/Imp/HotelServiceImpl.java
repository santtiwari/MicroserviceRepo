package com.lcwd.hotel.services.Imp;

import com.lcwd.hotel.entites.Hotel;
import com.lcwd.hotel.exception.ResourceNotFoundException;
import com.lcwd.hotel.repositories.HotelRepository;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class HotelServiceImpl implements HotelService {

    @Autowired

    private HotelRepository hotelrepo;

    @Override
    public Hotel create(Hotel hotel) {
       String hotelId = UUID.randomUUID().toString();
       hotel.setId(hotelId);

        return hotelrepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {

        return hotelrepo.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {

        return hotelrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel given id not found !!"));
    }
}
