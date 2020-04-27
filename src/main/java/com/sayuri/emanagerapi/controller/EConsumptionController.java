package com.sayuri.emanagerapi.controller;

import com.sayuri.emanagerapi.form.ConsumptionCalculateForm;
import com.sayuri.emanagerapi.form.ConsumptionCalculateResponse;
import com.sayuri.emanagerapi.form.ConsumptionEmailForm;
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
        EConsumption eConsumptionSaved = service.addOrUpdate(eConsumption);
        if(eConsumptionSaved == null)
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);

        return new ResponseEntity<>(eConsumptionSaved, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }

    @PostMapping("/consumption-calculate")
    public ResponseEntity<?> consumptionCalculate(@RequestBody ConsumptionCalculateForm consumptionCalculateForm) {
        ConsumptionCalculateResponse calculateResponse = service.calculate(consumptionCalculateForm.getCustomerCategoryId(), consumptionCalculateForm.getConsumptionValue());
        return new ResponseEntity<>(calculateResponse, HttpStatus.OK);
    }
    
}
