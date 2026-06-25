package com.premit.order_services.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private int orderId;
    private String orderStatus;
    private float orderAmount;
    private String emailId;
    private String city;
}
