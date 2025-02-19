package com.moments.WeatherAPI.Controllers;

import com.moments.WeatherAPI.Services.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public Map<String, Object> getWeather(@RequestParam String city) {
        return weatherService.getWeatherData(city);
    }
}

