package com.example.saul.taco_cloud.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.saul.taco_cloud.domain.TacoOrder;


public interface TacoOrderRepository extends JpaRepository<TacoOrder, Long> {
    
}
