package com.premit.order_services.controller;

import com.premit.order_services.DTO.OrdersDTO;
import com.premit.order_services.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersServiceController {

    @Autowired
    OrdersService ordersService;

    @PostMapping(path="/create")
    public String createOrder(@RequestBody OrdersDTO ordersDTO) {
        String ordersCreationResult = ordersService.createOrder(ordersDTO);
        return ordersCreationResult;
    }
}
