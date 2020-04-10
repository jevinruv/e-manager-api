package com.sayuri.emanagerapi.repository;

import com.sayuri.emanagerapi.model.CustomerCategoryPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCategoryPriceRepo extends JpaRepository<CustomerCategoryPrice, Integer> {
}
