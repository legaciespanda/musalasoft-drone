package com.musalasoft.drone.advice;

import com.musalasoft.drone.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

//@ControllerAdvice
public class CustomResponseEntityException {
//    @ExceptionHandler
//    public ResponseEntity<ApiResponse> handle(ConstraintViolationException constraintViolationException){
//        String errorMessage = new ArrayList<>(constraintViolationException.getConstraintViolations()).get(0).getMessage();
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setResponseMessage(errorMessage);
//        return new ResponseEntity<>(apiResponse, null, HttpStatus.BAD_REQUEST);
//    }
}
