package com.premit.order_services.controller;

import com.premit.order_services.DTO.OrdersDTO;
import com.premit.order_services.entity.OrdersEntity;
import com.premit.order_services.service.OrdersService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrdersServiceController {

    @Autowired
    OrdersService ordersService;

    @PostMapping(path = "/create")
    public String createOrder(@RequestBody OrdersDTO ordersDTO) {
        String ordersCreationResult = ordersService.createOrder(ordersDTO);
        return ordersCreationResult;
    }

    @GetMapping(path = "/status/{status}/email/{emailId}")
    public List<OrdersDTO> getOrdersByStatusAndEmail(@PathVariable("status") String orderStatus, @PathVariable String emailId) {
        List<OrdersDTO> retrievedOrdersDTOList = ordersService.getOrdersByStatusAndEmail(orderStatus, emailId);
        return retrievedOrdersDTOList;
    }

    @GetMapping(path = "/emailId/{emailId}/city/{city}")
    public List<OrdersDTO> getOrdersByEmailIdAndCity(@PathVariable Map<String, String> values) {
        String emailId = values.get("emailId");
        String city = values.get("city");
        List<OrdersDTO> retrievedOrdersDTOList = ordersService.getOrdersByEmailIdAndCity(emailId, city);
        return retrievedOrdersDTOList;
    }

    @DeleteMapping(path="/delete/{city}")
    public int deleteUsersByCity(@PathVariable String city){
       int deletedUsersByCity = ordersService.deleteUsersByCity(city);
        return deletedUsersByCity;
    }

    @PutMapping(path="/update/city/{city}/email/{emailId}")
    public int updateCityByEmailId(@PathVariable String city,@PathVariable(name="emailId") String email) {
        int updated = ordersService.updateCityByEmailId(city,email);
        return updated;
    }

    @GetMapping(path="/get/orders/email/{emailId}")
    public List<OrdersDTO> getOrdersBasedOnEmailId(@PathVariable(name="emailId") String email,
                                                      @RequestParam(name = "cityName",required = false) String city) {
       if(email!=null && city!=null) {
           List<OrdersDTO> retrievedOrdersDTOListByEmailAndCity = ordersService.getOrdersByEmailIdAndCity(email, city);
           return retrievedOrdersDTOListByEmailAndCity;
       } else {
           List<OrdersDTO> retrievedOrdersDTOListByEmail = ordersService.getOrdersByEmail(email);
           return retrievedOrdersDTOListByEmail;
       }
    }
}