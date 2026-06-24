package com.premit.order_services.DTO;

import lombok.Data;

@Data
public class OrdersDTO {
    private int orderId;
    private String orderStatus;
    private float orderAmount;
    private String emailId;
    private String city;
}
