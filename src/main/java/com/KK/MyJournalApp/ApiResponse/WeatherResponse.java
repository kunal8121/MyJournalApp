package com.KK.MyJournalApp.ApiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WeatherResponse {


    private Current current;

    @Getter
    @Setter
    public class Current {


        private int temperature;

        @JsonProperty("weather_descriptions")
        private List<String> weather_descriptions;

        private int feelslike;

    }
}




