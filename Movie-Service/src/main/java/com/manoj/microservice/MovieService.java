package com.manoj.microservice;

import com.manoj.microservice.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Service
public class MovieService {
    @Autowired
    WebClient.Builder webClientBuilder;
    public Movie getMovie(String id,String key){
        return webClientBuilder.build()
                .get()
                .uri("https://movie-database-alternative.p.rapidapi.com?i="+id)
                .headers(header->{
                    header.setContentType(MediaType.APPLICATION_JSON);
                    header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    header.set("X-RapidAPI-Key",key);
                    header.set("X-RapidAPI-Host","movie-database-alternative.p.rapidapi.com");
                })
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
    }
}
