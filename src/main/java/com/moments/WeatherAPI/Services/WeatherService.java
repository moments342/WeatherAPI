package com.moments.WeatherAPI.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {

    private final WebClient webClient;
    private final String apiKey;

    public WeatherService(@Value("${weather.api.key}") String apiKey, @Value("${weather.api.url}") String baseUrl) {
        this.apiKey = apiKey;
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Cacheable("weatherData")
    public String getWeather(String city) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{city}")
                        .queryParam("key", apiKey)
                        .build(city))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

