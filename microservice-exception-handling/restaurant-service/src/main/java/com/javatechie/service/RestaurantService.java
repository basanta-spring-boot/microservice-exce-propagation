package com.javatechie.service;

import com.javatechie.dao.RestaurantOrderDAO;
import com.javatechie.dto.GlobalErrorCode;
import com.javatechie.dto.OrderResponseDTO;
import com.javatechie.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantOrderDAO orderDAO;

    public String greeting() {
        return "Welcome to Swiggy Restaurant service";
    }

    public OrderResponseDTO getOrder(String orderId) throws OrderNotFoundException {
        OrderResponseDTO orders = orderDAO.getOrders(orderId);
        if (orders != null) {
            return orders;
        } else {
            throw new OrderNotFoundException("Invalid order id !", GlobalErrorCode.ERROR_ORDER_NOT_FOUND);
        }
    }
}
