package com.sayuri.emanagerapi.controller;

import com.sayuri.emanagerapi.model.CommonValue;
import com.sayuri.emanagerapi.repository.CommonValueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/common-values")
public class CommonValueController {

    @Autowired
    private CommonValueRepo repo;

    @GetMapping("/{key}")
    public Optional<CommonValue> get(@PathVariable String key) {
        return repo.findByKey(key);
    }

    @GetMapping
    public List<CommonValue> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public CommonValue addOrUpdate(@RequestBody CommonValue commonValue) {
        return repo.save(commonValue);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }

}
