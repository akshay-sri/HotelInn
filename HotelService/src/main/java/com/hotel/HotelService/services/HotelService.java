package com.hotel.HotelService.services;

import com.hotel.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel);
    Hotel getHotel(String hotelId);
    List<Hotel> gethotels();
    Hotel updateHotel(Hotel hotel,String hotelId);
    void  deleteHotel(String hotelId);
}
