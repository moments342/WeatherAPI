package com.moments.WeatherAPI.Services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {

    private final WebClient webClient;

    public WeatherService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline")
                .build();
    }

    @Cacheable("weatherData")
    public String getWeather(String city) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{city}")
                        .queryParam("key", "EXLKSZUA8L9GZFEVKY45CLQRA")
                        .build(city))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

