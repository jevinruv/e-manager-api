package com.sayuri.emanagerapi.repository;

import com.sayuri.emanagerapi.model.PredictionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionItemRepo extends JpaRepository<PredictionItem, Integer> {
}
