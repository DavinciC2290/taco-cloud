package com.example.saul.taco_cloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.saul.taco_cloud.domain.Taco;

public interface TacoRepository extends JpaRepository <Taco, Long>{
    
}
