package com.KK.MyJournalApp.service;

import com.KK.MyJournalApp.ApiResponse.WeatherResponse;
import com.KK.MyJournalApp.cache.AppChace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private  String apiKey;

//    private static final String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private AppChace appChace;

    @Autowired
    private RedisService redisService;


    @Autowired
    private RestTemplate restTemplate;

     public WeatherResponse getWeather(String city)
     {
         WeatherResponse weatherResponse=redisService.get("weather_of_" + city , WeatherResponse.class);
          if(weatherResponse!=null)
          {
              return weatherResponse;
          }
          else {
              String finalAPI = appChace.APP_CACHE.get("weather_api").replace("<city>", city).replace("<apiKey>", apiKey);
              ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
              WeatherResponse body = response.getBody();
              if (body != null)
                  redisService.set("weather_of_" + city, body, 300l);

              return body;
          }
     }

}
