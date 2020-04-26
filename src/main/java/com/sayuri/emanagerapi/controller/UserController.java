package com.sayuri.emanagerapi.controller;

import com.sayuri.emanagerapi.model.EConsumption;
import com.sayuri.emanagerapi.model.User;
import com.sayuri.emanagerapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo repo;

    @GetMapping("/{id}")
    public Optional<User> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<User> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public User addOrUpdate(@RequestBody User user) {

        Optional<User> userFound = repo.findByEmail(user.getEmail());
        if (user.getId() == 0 && userFound.isPresent())
            return null;

        return repo.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        Optional<User> signedUser = repo.findByEmailAndPassword(user.getEmail(), user.getPassword());
        return new ResponseEntity<>(signedUser, HttpStatus.OK);
    }
}
