package com.premit.order_services.controller;

import com.premit.order_services.DTO.OrdersDTO;
import com.premit.order_services.service.OrdersService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrdersServiceController {

    @Autowired
    OrdersService ordersService;

    @PostMapping(path = "/create")
    public ResponseEntity<String> createOrder(@RequestBody OrdersDTO ordersDTO) {
        String ordersCreationResult = ordersService.createOrder(ordersDTO);
        if(ordersCreationResult.equals("Order created successfully.")){
            return new ResponseEntity<>(ordersCreationResult, HttpStatusCode.valueOf(201));
        } else {
            return new ResponseEntity<>(ordersCreationResult,HttpStatusCode.valueOf(409));
        }
    }

    @GetMapping(path = "/status/{status}/email/{emailId}")
    public ResponseEntity<List<OrdersDTO>> getOrdersByStatusAndEmail(@PathVariable("status") String orderStatus, @PathVariable String emailId) {
        List<OrdersDTO> retrievedOrdersDTOList = ordersService.getOrdersByStatusAndEmail(orderStatus, emailId);
        if(retrievedOrdersDTOList!=null) {
            return new ResponseEntity<>(retrievedOrdersDTOList,HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(retrievedOrdersDTOList,HttpStatusCode.valueOf(200));
    }

    @GetMapping(path = "/emailId/{emailId}/city/{city}")
    public List<OrdersDTO> getOrdersByEmailIdAndCity(@PathVariable Map<String, String> values) {
        String emailId = values.get("emailId");
        String city = values.get("city");
        List<OrdersDTO> retrievedOrdersDTOList = ordersService.getOrdersByEmailIdAndCity(emailId, city);
        return retrievedOrdersDTOList;
    }

    @DeleteMapping(path="/delete/{city}")
    public ResponseEntity<Integer> deleteUsersByCity(@PathVariable String city){
       int deletedUsersByCity = ordersService.deleteUsersByCity(city);
        if(deletedUsersByCity==0){
            return new ResponseEntity<>(deletedUsersByCity,HttpStatusCode.valueOf(404));
        } else{
            return new ResponseEntity<>(deletedUsersByCity,HttpStatusCode.valueOf(200));
        }
    }

    @PutMapping(path="/update/city/{city}/email/{emailId}")
    public ResponseEntity<Integer> updateCityByEmailId(@PathVariable String city,@PathVariable(name="emailId") String email) {
        int updated = ordersService.updateCityByEmailId(city,email);
        if(updated==0){
            return new ResponseEntity<>(updated,HttpStatusCode.valueOf(404));
        } else{
            return new ResponseEntity<>(updated,HttpStatusCode.valueOf(200));
        }
    }

    @GetMapping(path="/get/orders/email/{emailId}")
    public ResponseEntity<List<OrdersDTO>> getOrdersBasedOnEmailId(@PathVariable(name="emailId") String email,
                                                      @RequestParam(name = "cityName",required = false) String city) {
       if(email!=null && city!=null) {
           List<OrdersDTO> retrievedOrdersDTOListByEmailAndCity = ordersService.getOrdersByEmailIdAndCity(email, city);
           if(retrievedOrdersDTOListByEmailAndCity!=null){
               return new ResponseEntity<>(retrievedOrdersDTOListByEmailAndCity,HttpStatusCode.valueOf(200));
           } else {
               return new ResponseEntity<>(retrievedOrdersDTOListByEmailAndCity,HttpStatusCode.valueOf(204));
           }
       } else {
           List<OrdersDTO> retrievedOrdersDTOListByEmail = ordersService.getOrdersByEmail(email);
           if(retrievedOrdersDTOListByEmail!=null){
               return new ResponseEntity<>(retrievedOrdersDTOListByEmail,HttpStatusCode.valueOf(200));
           } else {
               return new ResponseEntity<>(retrievedOrdersDTOListByEmail,HttpStatusCode.valueOf(404));
           }
       }
    }

    @GetMapping("/order/status/id")
    public List<String> getOrderStatusByIds(@RequestParam List<Integer> id) {
        List<String> retrievedOrdersStatus = ordersService.getOrderStatusByIds(id);
        return retrievedOrdersStatus;
    }

    @GetMapping("/orders/city/{cityName}")
    public ResponseEntity<List<OrdersDTO>> getOrdersByCity(@PathVariable(name="cityName") String city) {
        List<OrdersDTO> retrievedOrders = ordersService.getOrdersByCity(city);
        if(retrievedOrders!=null){
            return new ResponseEntity<>(retrievedOrders,HttpStatusCode.valueOf(200));
        } else {
            return new ResponseEntity<>(retrievedOrders,HttpStatusCode.valueOf(404));
        }
    }

    @GetMapping("/orders/filters")
    public List<OrdersDTO> getOrdersByFilters(@RequestParam Map<String,String> values) {
        List<OrdersDTO> ordersDTOList = ordersService.getOrdersByFilters(values);
        return ordersDTOList;
    }
}













