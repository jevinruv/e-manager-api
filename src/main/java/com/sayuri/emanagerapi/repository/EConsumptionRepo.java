package com.sayuri.emanagerapi.repository;

import com.sayuri.emanagerapi.model.EConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EConsumptionRepo extends JpaRepository<EConsumption, Integer> {
    Optional<EConsumption> findByConsumptionDate(Date consumptionDate);
}
