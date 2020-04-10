package com.sayuri.emanagerapi.controller;

import com.sayuri.emanagerapi.model.CustomerCategoryPrice;
import com.sayuri.emanagerapi.repository.CustomerCategoryPriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer-category-prices")
public class CustomerCategoryPriceController {

    @Autowired
    private CustomerCategoryPriceRepo repo;

    @GetMapping("/{id}")
    public Optional<CustomerCategoryPrice> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<CustomerCategoryPrice> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public CustomerCategoryPrice addOrUpdate(@RequestBody CustomerCategoryPrice customerCategoryPrice) {
        return repo.save(customerCategoryPrice);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }

}
