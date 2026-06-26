package com.premit.order_services.service;

import com.premit.order_services.DTO.OrdersDTO;

import java.util.List;

public interface OrdersService {
    public String createOrder(OrdersDTO ordersDTO);
    List<OrdersDTO> getOrdersByStatusAndEmail(String orderStatus,String emailId);
    List<OrdersDTO> getOrdersByEmailIdAndCity(String emailId,String city);
    int deleteUsersByCity(String city);
}
