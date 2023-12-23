package com.user.UserService.controllers;

import com.user.UserService.entities.User;
import com.user.UserService.payloads.ApiResponse;
import com.user.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

//    @PostMapping("/create")
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User user1 = userService.createUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
//    }
    int retryCount=1;

    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelRetry",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        logger.info("Retry count: {}", retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    //fallback method
    public ResponseEntity<User> ratingHotelFallback(String userId,Exception e){
        logger.info("Fallback is executed because service is down",e.getMessage());
        User user=User.builder()
                .name("Dummy")
                .email("dummy@gmail.com")
                .about("The user is created dummy because some services are down")
                .userId("141234")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
        User user1 = userService.updateUser(user, userId);
        return ResponseEntity.ok(user1);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity(new ApiResponse("User Deleted successfully"), HttpStatus.OK);
    }
}
