package com.hotel.HotelService.exceptions;

import com.hotel.HotelService.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse>handleResourceNotFoundException(ResourceNotFoundException ex){
        ApiResponse response=new ApiResponse(ex.getMessage());
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }
}
