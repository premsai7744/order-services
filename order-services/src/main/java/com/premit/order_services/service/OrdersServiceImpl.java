package com.premit.order_services.service;

import com.premit.order_services.DTO.OrdersDTO;
import com.premit.order_services.entity.OrdersEntity;
import com.premit.order_services.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public String createOrder(OrdersDTO ordersDTO) {
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setOrderId(ordersDTO.getOrderId());
        ordersEntity.setOrderAmount(ordersDTO.getOrderAmount());
        ordersEntity.setOrderStatus(ordersDTO.getOrderStatus());
        ordersEntity.setCity(ordersDTO.getCity());
        ordersEntity.setEmailId(ordersDTO.getEmailId());

        Optional<OrdersEntity> retrevedOptionalOrdersEntity = ordersRepository.findById(ordersEntity.getOrderId());
        if(retrevedOptionalOrdersEntity.isPresent()){
            return "Order details already available.";
        } else {
           OrdersEntity savedOrder = ordersRepository.save(ordersEntity);
            if(savedOrder!=null){
                return "Order created successfully.";
            } else {
                return "order creation failed.";
            }
        }
    }
}
