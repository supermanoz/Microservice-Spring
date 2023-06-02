package com.manoj.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/movies")
public class MovieController {


    @Autowired
    private MovieService movieService;
    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> getMovie(@PathVariable String id, @RequestHeader("X-RapidAPI-Key") String key){
        return ResponseEntity.ok().body(movieService.getMovie(id,key));
    }
}
