package com.premit.order_services.service;

import com.premit.order_services.DTO.OrdersDTO;

import java.util.List;
import java.util.Map;

public interface OrdersService {
    public String createOrder(OrdersDTO ordersDTO);
    List<OrdersDTO> getOrdersByStatusAndEmail(String orderStatus,String emailId);
    List<OrdersDTO> getOrdersByEmailIdAndCity(String emailId,String city);
    int deleteUsersByCity(String city);
    int updateCityByEmailId(String city, String email);
    List<OrdersDTO> getOrdersByEmail(String email);
    List<String> getOrderStatusByIds(List<Integer> id);
    List<OrdersDTO> getOrdersByCity(String city);
    List<OrdersDTO> getOrdersByFilters(Map<String,String> values);
}
