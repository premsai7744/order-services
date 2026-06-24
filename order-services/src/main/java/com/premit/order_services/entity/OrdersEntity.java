package com.premit.order_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ordrs")
public class OrdersEntity {
    @Id
    @Column(name="order_id")
    private int orderId;

    @Column(name="order_status")
    private String orderStatus;

    @Column(name="order_amount")
    private float orderAmount;

    @Column(name="email_id")
    private String emailId;

    @Column(name="city")
    private String city;
}
