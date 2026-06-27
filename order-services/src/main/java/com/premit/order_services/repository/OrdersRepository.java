package com.premit.order_services.repository;

import com.premit.order_services.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity,Integer> {
    Optional<List<OrdersEntity>> findByOrderStatusAndEmailId(String orderStatus, String emailId);
    Optional<List<OrdersEntity>> findByEmailIdAndCity(String emailId,String city);
     int deleteByCity(String city);

     @Modifying
     @Query(value = "UPDATE ordrs SET city = :cityName WHERE email_id = :email",nativeQuery = true)
     int updateCityByEmail(@Param("cityName") String city, @Param("email") String email);

     Optional<List<OrdersEntity>> findByEmailId(String email);
}

