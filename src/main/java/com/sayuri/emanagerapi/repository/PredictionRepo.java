package com.sayuri.emanagerapi.repository;

import com.sayuri.emanagerapi.model.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepo extends JpaRepository<Prediction, Integer> {

    @Query(value = "SELECT nextval('prediction_req_seq')", nativeQuery = true)
    int getNextPredictionSeq();
}
