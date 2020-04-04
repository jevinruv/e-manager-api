package com.sayuri.emanagerapi.repository;

import com.sayuri.emanagerapi.model.EConsumption;
import com.sayuri.emanagerapi.model.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EConsumptionRepo extends JpaRepository<EConsumption, Integer> {
}
