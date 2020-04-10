package com.sayuri.emanagerapi.repository;

import com.sayuri.emanagerapi.model.CustomerCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCategoryRepo extends JpaRepository<CustomerCategory, Integer> {
}
