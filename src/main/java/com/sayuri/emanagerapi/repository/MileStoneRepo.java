package com.sayuri.emanagerapi.repository;

import com.sayuri.emanagerapi.model.MileStone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface MileStoneRepo extends JpaRepository<MileStone, Integer> {
    Optional<MileStone> findByMileStoneDate(Date mileStoneDate);
}
