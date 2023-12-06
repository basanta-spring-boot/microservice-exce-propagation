package com.javatechie.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.dto.CustomErrorResponse;
import com.javatechie.dto.GlobalErrorCode;
import com.javatechie.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@RestControllerAdvice
@Slf4j
public class RestaurantServiceExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleException(OrderNotFoundException ex) throws JsonProcessingException {
        log.info("RestaurantServiceExceptionHandler::handleException called..." + ex.getMessage());
        CustomErrorResponse errorResponse=new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),GlobalErrorCode.ERROR_ORDER_NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

//        return ResponseEntity.internalServerError()
//                .body(errorResponse);

//        return ResponseEntity.of(Optional.of(CustomErrorResponse.builder()
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .errorMessage(ex.getMessage())
//                .errorCode(GlobalErrorCode.ERROR_ORDER_NOT_FOUND)
//                .build()));
    }
}
