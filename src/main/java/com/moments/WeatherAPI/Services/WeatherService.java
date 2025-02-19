package com.moments.WeatherAPI.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class WeatherService {

    private final WebClient webClient;
    private final String apiKey;
    private final ObjectMapper objectMapper;

    public WeatherService(@Value("${weather.api.key}") String apiKey,
                          @Value("${weather.api.url}") String baseUrl) {
        this.apiKey = apiKey;
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
        this.objectMapper = new ObjectMapper(); 
    }

    public Map<String, Object> getWeatherData(String city) {
        String jsonResponse = this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{city}")
                        .queryParam("key", apiKey)
                        .build(city))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            Map<String, Object> responseMap = objectMapper.readValue(jsonResponse, Map.class);
            return extractRelevantData(responseMap); // Extrai os dados necessários
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar o JSON recebido da API", e);
        }
    }

    private Map<String, Object> extractRelevantData(Map<String, Object> responseMap) {

        String city = (String) responseMap.get("address"); // Cidade consultada
        Map<String, Object> currentConditions = (Map<String, Object>) responseMap.get("currentConditions");

        return Map.of(
                "city", city,
                "temperature", currentConditions.get("temp"),
                "feelsLike", currentConditions.get("feelslike"),
                "conditions", currentConditions.get("conditions")
        );
    }
}

