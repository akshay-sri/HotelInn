package com.auth.AuthService.controller;

import com.auth.AuthService.entity.AuthRequest;
import com.auth.AuthService.entity.UserCredential;
import com.auth.AuthService.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/register")
    public ResponseEntity<UserCredential> createUser(@RequestBody UserCredential user) {
        UserCredential user1 = authService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest){
        Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return authService.generateToken(authRequest.getUsername());
        }
        else{
            throw new RuntimeException("invalid access");
        }

    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        authService.validateToken(token);
        return "Token validated";
    }
}
