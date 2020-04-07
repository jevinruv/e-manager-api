package com.sayuri.emanagerapi.controller;

import com.sayuri.emanagerapi.model.ForumAnswer;
import com.sayuri.emanagerapi.repository.ForumAnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/forum-answers")
public class ForumAnswerController {

    @Autowired
    private ForumAnswerRepo repo;

    @GetMapping("/{id}")
    public Optional<ForumAnswer> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<ForumAnswer> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public ForumAnswer addOrUpdate(@RequestBody ForumAnswer forumAnswer) {
        return repo.save(forumAnswer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }

}
