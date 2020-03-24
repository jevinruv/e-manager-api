package com.sayuri.emanagerapi.controller;

import com.sayuri.emanagerapi.model.User;
import com.sayuri.emanagerapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/{id}")
    public Optional<User> get(@PathVariable int id) {
        return userRepo.findById(id);
    }

    @GetMapping
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @PostMapping
    public User addOrUpdate(@RequestBody User user) {
        return userRepo.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userRepo.deleteById(id);
    }
}
