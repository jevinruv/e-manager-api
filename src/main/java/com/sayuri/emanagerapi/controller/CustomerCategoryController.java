package com.sayuri.emanagerapi.controller;

import com.sayuri.emanagerapi.model.CustomerCategory;
import com.sayuri.emanagerapi.repository.CustomerCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer-categories")
public class CustomerCategoryController {

    @Autowired
    private CustomerCategoryRepo repo;

    @GetMapping("/{id}")
    public Optional<CustomerCategory> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<CustomerCategory> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public CustomerCategory addOrUpdate(@RequestBody CustomerCategory customerCategory) {
        return repo.save(customerCategory);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }

}
