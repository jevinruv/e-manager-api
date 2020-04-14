package com.sayuri.emanagerapi.controller;

import com.sayuri.emanagerapi.model.MileStone;
import com.sayuri.emanagerapi.repository.MileStoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/milestones")
public class MileStoneController {

    @Autowired
    private MileStoneRepo repo;

    @GetMapping("/{id}")
    public Optional<MileStone> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<MileStone> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public MileStone addOrUpdate(@RequestBody MileStone mileStone) {
        return repo.save(mileStone);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }

}
