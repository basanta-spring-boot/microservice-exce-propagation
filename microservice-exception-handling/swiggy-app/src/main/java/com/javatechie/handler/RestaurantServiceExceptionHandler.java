package com.javatechie.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.client.MicroserviceAException;
import com.javatechie.dto.CustomErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
@Slf4j
public class RestaurantServiceExceptionHandler {

    @ExceptionHandler(MicroserviceAException.class)
    public ResponseEntity<CustomErrorResponse> handleException(MicroserviceAException ex) throws JsonProcessingException {
        log.info("RestaurantServiceExceptionHandler::handleException called..." + ex.getMessage());
        CustomErrorResponse exResponseBodyAs = new ObjectMapper().readValue(ex.getMessage(), CustomErrorResponse.class);
        return new ResponseEntity<>(exResponseBodyAs, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
