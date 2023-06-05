package com.manoj.microservice.service;

import com.manoj.microservice.model.Movie;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Service
public class MovieCatalogService {

    @Autowired
    WebClient.Builder webClientBuilder;
    @HystrixCommand(fallbackMethod = "getDefaultMovie",
    commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public Movie getMovie(String imdbID, String key){
        Movie movie=webClientBuilder.build()
                .get()
                .uri("http://movie-app/movies/fetch/"+imdbID)
                .headers(header -> {
                    header.setContentType(MediaType.APPLICATION_JSON);
                    header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    header.set("X-RapidAPI-Key",key);
                })
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
        return movie;
    }

    public Movie getDefaultMovie(String imdbID, String key){
        return Movie.builder()
                .imdbID(imdbID)
                .title("Not Found!")
                .build();
    }
}
