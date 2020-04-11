package com.sayuri.emanagerapi.controller;

import com.sayuri.emanagerapi.form.ConsumptionCalculateForm;
import com.sayuri.emanagerapi.form.PredictionForm;
import com.sayuri.emanagerapi.model.EConsumption;
import com.sayuri.emanagerapi.model.Prediction;
import com.sayuri.emanagerapi.repository.EConsumptionRepo;
import com.sayuri.emanagerapi.service.EConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/econsumptions")
public class EConsumptionController {

    @Autowired
    private EConsumptionService service;

    @Autowired
    private EConsumptionRepo repo;

    @GetMapping("/{id}")
    public Optional<EConsumption> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<EConsumption> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addOrUpdate(@RequestBody EConsumption eConsumption) {
        Optional<EConsumption> consumptionFound = repo.findByConsumptionDate(eConsumption.getConsumptionDate());
        if(eConsumption.getId() == 0 && consumptionFound.isPresent())
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);

        EConsumption eConsumptionSaved = repo.save(eConsumption);
        return new ResponseEntity<>(eConsumptionSaved, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }

    @PostMapping("/consumption-calculate")
    public ResponseEntity<?> consumptionCalculate(@RequestBody ConsumptionCalculateForm consumptionCalculateForm) {
        double consumption = service.calculate(consumptionCalculateForm.getCustomerCategoryId(), consumptionCalculateForm.getConsumptionValue());
        return new ResponseEntity<>(consumption, HttpStatus.OK);
    }
}
