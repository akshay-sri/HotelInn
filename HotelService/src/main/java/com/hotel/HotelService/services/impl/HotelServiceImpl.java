package com.hotel.HotelService.services.impl;

import com.hotel.HotelService.entities.Hotel;
import com.hotel.HotelService.exceptions.ResourceNotFoundException;
import com.hotel.HotelService.repositories.HotelRepo;
import com.hotel.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepo hotelRepo;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setHotelId(randomHotelId);
        return hotelRepo.save(hotel);
    }

    @Override
    public Hotel getHotel(String hotelId) {
        Hotel hotel = hotelRepo.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel", " id ", hotelId));
        return hotel;
    }

    @Override
    public List<Hotel> gethotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel updateHotel(Hotel hotel, String hotelId) {
        Hotel hotel1 = hotelRepo.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel", " id ", hotelId));
        hotel1.setHotelName(hotel.getHotelName());
        hotel1.setAbout(hotel.getAbout());
        hotel1.setLocation(hotel.getLocation());
        return hotelRepo.save(hotel1);
    }

    @Override
    public void deleteHotel(String hotelId) {
        Hotel hotel = hotelRepo.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel", " id ", hotelId));
        hotelRepo.delete(hotel);
    }
}
