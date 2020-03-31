package com.sayuri.emanagerapi.controller;

import com.sayuri.emanagerapi.form.PredictionForm;
import com.sayuri.emanagerapi.model.Prediction;
import com.sayuri.emanagerapi.model.User;
import com.sayuri.emanagerapi.repository.PredictionRepo;
import com.sayuri.emanagerapi.repository.UserRepo;
import com.sayuri.emanagerapi.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/predictions")
public class PredictionController {

    @Autowired
    private PredictionService service;

    @Autowired
    private PredictionRepo repo;

    @GetMapping("/{id}")
    public Optional<Prediction> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<Prediction> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Prediction addOrUpdate(@RequestBody Prediction prediction) {
        return repo.save(prediction);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }

    @PostMapping("/predict")
    public ResponseEntity<?> predict(@RequestBody PredictionForm predictionForm) {
        Prediction prediction = service.getPrediction(predictionForm.getFrequency(), predictionForm.getDuration());
        return new ResponseEntity<>(prediction, HttpStatus.OK);
    }
}
