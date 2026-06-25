package com.premit.order_services.controller;

import com.premit.order_services.DTO.OrdersDTO;
import com.premit.order_services.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdersServiceController {

    @Autowired
    OrdersService ordersService;

    @PostMapping(path="/create")
    public String createOrder(@RequestBody OrdersDTO ordersDTO) {
        String ordersCreationResult = ordersService.createOrder(ordersDTO);
        return ordersCreationResult;
    }

    @GetMapping(path="/status/{status}/email/{emailId}")
    public List<OrdersDTO> getOrdersByStatusAndEmail(@PathVariable("status") String orderStatus, @PathVariable String emailId) {
    List<OrdersDTO> retrievedOrdersDTOList = ordersService.getOrdersByStatusAndEmail(orderStatus,emailId);
        return retrievedOrdersDTOList;
    }
}
