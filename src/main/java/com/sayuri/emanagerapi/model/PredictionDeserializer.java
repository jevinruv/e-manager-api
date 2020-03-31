package com.sayuri.emanagerapi.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class PredictionDeserializer extends JsonDeserializer<Prediction> {
    @Override
    public Prediction deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        JsonNode node = p.getCodec().readTree(p);

        String consumptionDate = node.get("ds").textValue();
        double consumptionLower = node.get("Consumption_lower").doubleValue();
        double consumption = node.get("Consumption").doubleValue();
        double consumptionUpper = node.get("Consumption_upper").doubleValue();

        Prediction prediction = new Prediction();
        prediction.setConsumptionDate(consumptionDate);
        prediction.setConsumptionLower(consumptionLower);
        prediction.setConsumption(consumption);
        prediction.setConsumptionUpper(consumptionUpper);

        return prediction;
    }
}
