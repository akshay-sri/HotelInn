package com.hotel.HotelService.controllers;

import com.hotel.HotelService.entities.Hotel;
import com.hotel.HotelService.payloads.ApiResponse;
import com.hotel.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel hotel1 = hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
        Hotel hotel1 = hotelService.getHotel(hotelId);
        return ResponseEntity.ok(hotel1);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels() {
        List<Hotel> hotels = hotelService.gethotels();
        return ResponseEntity.ok(hotels);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable String hotelId) {
        Hotel hotel1 = hotelService.updateHotel(hotel, hotelId);
        return ResponseEntity.ok(hotel1);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String hotelId) {
        hotelService.deleteHotel(hotelId);
        return new ResponseEntity(new ApiResponse("Hotel deleted successfully"), HttpStatus.OK);
    }
}
