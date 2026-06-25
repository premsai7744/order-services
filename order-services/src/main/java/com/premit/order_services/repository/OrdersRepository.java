package com.premit.order_services.repository;

import com.premit.order_services.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity,Integer> {
    Optional<List<OrdersEntity>> findByOrderStatusAndEmailId(String orderStatus, String emailId);
}
