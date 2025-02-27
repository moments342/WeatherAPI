# Weather API
This API provides real-time weather data based on the name of a city. It integrates with a third-party weather service to fetch the weather data and returns essential information such as temperature, feels-like temperature, and weather conditions.

# Tecnologies used
• Java 21

• Spring

• Maven

# How To Use

### Prerequisites
• Java 21

• API Key from: https://www.visualcrossing.com/weather-api/

• Postman (To consume the API)


### Environment Configuration
In the `application.properties` file, add your API Key:
``` Properties
weather.api.key=YOUR_API_KEY
```

### How to consume the API
```
GET http://localhost:8080/weather?city="Name of the city"
```
