package com.javatechie.exception;

public class OrderNotFoundException extends Exception{

    private String errorCode;

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message,String errorCode) {
        super(message);
    }
}
