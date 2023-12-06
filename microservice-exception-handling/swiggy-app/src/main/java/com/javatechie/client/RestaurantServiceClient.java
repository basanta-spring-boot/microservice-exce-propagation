package com.javatechie.client;

import com.javatechie.dto.CustomErrorResponse;
import com.javatechie.dto.OrderResponseDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestaurantServiceClient {
    @Autowired
    private RestTemplate template;

    public ResponseEntity<?> fetchOrderStatus(String orderId) {
        ResponseEntity<OrderResponseDTO> response = null;
        try {
            response = template.getForEntity("http://RESTAURANT-SERVICE/restaurant/orders/status/" + orderId, OrderResponseDTO.class);
            // Process successful response from Microservice B
            System.out.println("Microservice B Response: " + response);
        } catch (HttpServerErrorException ex) {
            // Handle HTTP errors
//            CustomErrorResponse exResponseBodyAs = ex.getResponseBodyAs(CustomErrorResponse.class);
//            System.out.println("-----"+exResponseBodyAs);
            String responseBody = ex.getResponseBodyAsString();
            // Parse and handle the error response from Microservice B
            System.out.println("Microservice B error Response: " + responseBody);
            throw new MicroserviceAException(responseBody);
        } catch (Exception ex) {
            // Handle other exceptions
            System.err.println("Generic error: " + ex.getMessage());
            // Propagate a generic error to the client or perform additional error handling
            throw new MicroserviceAException("Error calling Restaurant Microservice ");
        }

        return response;
    }
}
