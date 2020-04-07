package com.sayuri.emanagerapi.controller;

import com.sayuri.emanagerapi.model.ForumQuestion;
import com.sayuri.emanagerapi.repository.ForumQuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/forum-questions")
public class ForumQuestionController {

    @Autowired
    private ForumQuestionRepo repo;

    @GetMapping("/{id}")
    public Optional<ForumQuestion> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<ForumQuestion> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public ForumQuestion addOrUpdate(@RequestBody ForumQuestion forumQuestion) {
        return repo.save(forumQuestion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }

}
