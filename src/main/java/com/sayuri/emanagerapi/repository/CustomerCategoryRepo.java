package com.sayuri.emanagerapi.repository;

import com.sayuri.emanagerapi.model.CustomerCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerCategoryRepo extends JpaRepository<CustomerCategory, Integer> {
    Optional<CustomerCategory> findByName(String name);
}
