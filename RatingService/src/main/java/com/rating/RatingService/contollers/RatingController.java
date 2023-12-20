package com.rating.RatingService.contollers;

import com.rating.RatingService.entities.Rating;
import com.rating.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating rating1=ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }
    @GetMapping
    public ResponseEntity<List<Rating>> getRatings(){
        List<Rating> ratings=ratingService.getRatings();
        return ResponseEntity.ok(ratings);
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUser(@PathVariable String userId){
        List<Rating> ratings=ratingService.getRatingByUserId(userId);
        return ResponseEntity.ok(ratings);
    }
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotel(@PathVariable String hotelId){
        List<Rating> ratings=ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.ok(ratings);
    }
}
