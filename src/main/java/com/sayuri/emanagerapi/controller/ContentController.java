package com.sayuri.emanagerapi.controller;

import com.sayuri.emanagerapi.model.Content;
import com.sayuri.emanagerapi.repository.ContentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentRepo repo;

    @GetMapping("/{id}")
    public Optional<Content> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<Content> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Content addOrUpdate(@RequestBody Content content) {
        return repo.save(content);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }

}
