package com.sayuri.emanagerapi.service;

import com.sayuri.emanagerapi.model.Prediction;
import com.sayuri.emanagerapi.model.PredictionItem;
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
public class PredictionService {

    @Autowired
    private PredictionRepo repo;

    private String predictionAPI = "https://e-manager-model.herokuapp.com/predict";

    public Prediction getPrediction(String frequency, int duration){

        List<PredictionItem> predictionItemList = makeAPICall(frequency, duration);

        if(predictionItemList.size() > 0){
//            predictionItemList.stream().forEach(predictionItem -> predictionItem.setPrediction(predictionSeq));
            Prediction prediction = new Prediction();
            prediction.setFrequency(frequency);
            prediction.setDuration(duration);
            prediction.setPredictionItems(predictionItemList);

            return repo.save(prediction);
        }

        return null;
    }

    private List<PredictionItem> makeAPICall(String frequency, int duration){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // request body parameters
        Map<String, Object> request = new HashMap<>();
        request.put("predict_freq", frequency);
        request.put("duration", duration);

        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<PredictionItem[]> consumptionPredictionResponse =
                restTemplate.postForEntity(predictionAPI, entity, PredictionItem[].class);

        if (!consumptionPredictionResponse.getStatusCode().is2xxSuccessful()) {
            return null;
        }

        PredictionItem[] predictionItems = consumptionPredictionResponse.getBody();
        return Arrays.asList(predictionItems);
    }
}
