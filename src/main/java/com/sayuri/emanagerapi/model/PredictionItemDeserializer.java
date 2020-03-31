package com.sayuri.emanagerapi.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class PredictionItemDeserializer extends JsonDeserializer<PredictionItem> {
    @Override
    public PredictionItem deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        JsonNode node = p.getCodec().readTree(p);

        String consumptionDate = node.get("ds").textValue();
        double consumptionLower = node.get("Consumption_lower").doubleValue();
        double consumption = node.get("Consumption").doubleValue();
        double consumptionUpper = node.get("Consumption_upper").doubleValue();
        
        PredictionItem predictionItem = new PredictionItem();
        predictionItem.setConsumptionDate(consumptionDate);
        predictionItem.setConsumptionLower(consumptionLower);
        predictionItem.setConsumption(consumption);
        predictionItem.setConsumptionUpper(consumptionUpper);

        return predictionItem;
    }
}
