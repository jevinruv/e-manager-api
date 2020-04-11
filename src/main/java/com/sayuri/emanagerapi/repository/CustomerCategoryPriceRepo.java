package com.sayuri.emanagerapi.repository;

import com.sayuri.emanagerapi.model.CustomerCategoryPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerCategoryPriceRepo extends JpaRepository<CustomerCategoryPrice, Integer> {
    Optional<CustomerCategoryPrice> findFirstByCustomerCategoryIdOrderByConsumptionRangeStopDesc(int customerCategoryId);
}
