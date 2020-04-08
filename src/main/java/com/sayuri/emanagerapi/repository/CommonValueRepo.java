package com.sayuri.emanagerapi.repository;

import com.sayuri.emanagerapi.model.CommonValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommonValueRepo extends JpaRepository<CommonValue, Integer> {
    Optional<CommonValue> findByKey(String key);
}
