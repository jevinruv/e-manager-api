package com.sayuri.emanagerapi.service;

import com.sayuri.emanagerapi.model.Prediction;
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

    public List<Prediction> getPrediction(String frequency, int duration){

        List<Prediction> predictionList = makeAPICall(frequency, duration);

        if(predictionList.size() > 0){
            int predictionSeq = repo.getNextPredictionSeq();
            predictionList.stream().forEach(prediction -> prediction.setPredictionSeq(predictionSeq));

            return repo.saveAll(predictionList);
        }

        return null;
    }

    private List<Prediction> makeAPICall(String frequency, int duration){

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

        ResponseEntity<Prediction[]> consumptionPredictionResponse =
                restTemplate.postForEntity(predictionAPI, entity, Prediction[].class);

        if (!consumptionPredictionResponse.getStatusCode().is2xxSuccessful()) {
            return null;
        }

        Prediction[] predictions = consumptionPredictionResponse.getBody();
        return Arrays.asList(predictions);
    }
}
