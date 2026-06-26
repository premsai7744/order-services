package com.premit.order_services.service;

import com.premit.order_services.DTO.OrdersDTO;
import com.premit.order_services.entity.OrdersEntity;
import com.premit.order_services.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<OrdersDTO> getOrdersByStatusAndEmail(String orderStatus, String emailId) {
        List<OrdersDTO> ordersDtoList = null;
        Optional<List<OrdersEntity>> optionalOrdersEntityList = ordersRepository.findByOrderStatusAndEmailId(orderStatus,emailId);
        if(optionalOrdersEntityList.isPresent()) {
            List<OrdersEntity> ordersEntityList = optionalOrdersEntityList.get();
            ordersDtoList = ordersEntityList.stream().map((entity)->{
                OrdersDTO ordersDTO = new OrdersDTO();
                ordersDTO.setOrderAmount(entity.getOrderAmount());
                ordersDTO.setOrderStatus(entity.getOrderStatus());
                ordersDTO.setOrderId(entity.getOrderId());
                ordersDTO.setCity(entity.getCity());
                ordersDTO.setEmailId(entity.getEmailId());
                return ordersDTO;
            }).collect(Collectors.toList());
            return ordersDtoList;
        } else {
            return ordersDtoList;
        }
    }

    @Override
    public List<OrdersDTO> getOrdersByEmailIdAndCity(String emailId, String city) {
        List<OrdersDTO> ordersDTOList = null;
        if(ordersRepository.findByEmailIdAndCity(emailId,city).isPresent()) {
            List<OrdersEntity> ordersEntityList = ordersRepository.findByEmailIdAndCity(emailId,city).get();
            ordersDTOList = ordersEntityList.stream().map(ordersEntity->new OrdersDTO(
                    ordersEntity.getOrderId(),
                    ordersEntity.getOrderStatus(),
                    ordersEntity.getOrderAmount(),
                    ordersEntity.getCity(),
                    ordersEntity.getEmailId()
                    )).collect(Collectors.toList());
            return ordersDTOList;
        } else {
            return ordersDTOList;
        }
    }

    @Transactional
    @Override
    public int deleteUsersByCity(String city) {
        int deletedByCity = ordersRepository.deleteByCity(city);
        return deletedByCity;
    }

    @Transactional
    @Override
    public int updateCityByEmailId(String city,String email) {
        int updated = ordersRepository.updateCityByEmail(city,email);
        return updated;
    }


}
