package com.sayuri.emanagerapi.service;

import com.sayuri.emanagerapi.model.EConsumption;
import com.sayuri.emanagerapi.model.Prediction;
import com.sayuri.emanagerapi.model.PredictionItem;
import com.sayuri.emanagerapi.repository.EConsumptionRepo;
import com.sayuri.emanagerapi.repository.PredictionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class EConsumptionService {

    @Autowired
    private EConsumptionRepo repo;

    public EConsumption addOrSave(EConsumption eConsumption){

        Calendar cal = Calendar.getInstance();
        cal.setTime(eConsumption.getConsumptionDate());
        int weekNo = cal.get(Calendar.WEEK_OF_YEAR);
        eConsumption.setWeekNo(weekNo);

        return repo.save(eConsumption);
    }
}
