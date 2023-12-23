package com.user.UserService.services.impl;

import com.user.UserService.entities.Hotel;
import com.user.UserService.entities.Rating;
import com.user.UserService.entities.User;
import com.user.UserService.exceptions.ResourceNotFoundException;
import com.user.UserService.repositories.UserRepo;
import com.user.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
        //fetch rating for above user
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+user.getUserId(), Rating[].class);
        List<Rating> ratings=Arrays.stream(ratingOfUser).toList();
        List<Rating> ratingList=ratings.stream().map(rating -> {
            ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
            Hotel hotel=forEntity.getBody();
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
        userRepo.delete(user);
    }

    @Override
    public User updateUser(User user, String userId) {
        User user1 = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
        user1.setName(user.getName());
        user1.setAbout(user.getAbout());
        user1.setEmail(user.getEmail());
        userRepo.save(user1);
        return user1;
    }
}
