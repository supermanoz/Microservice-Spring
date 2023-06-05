package com.manoj.microservice;

import com.manoj.microservice.pojo.RatingRequestPojo;
import com.manoj.microservice.service.RatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @GetMapping("/fetchAll")
//    @HystrixCommand(fallbackMethod = "getDefaultRatings")
    public ResponseEntity<?> getRatings(@RequestHeader("X-RapidAPI-Key") String key){
        return ResponseEntity.ok(ratingService.getRatings(key));
    }

//    public ResponseEntity<?> getDefaultRatings(@RequestHeader("X-RapidAPI-Key") String key) {
//        return ResponseEntity.ok("I am a fallback method");
//    }

    @PostMapping("/save")
    public ResponseEntity<?> addRating(@RequestBody RatingRequestPojo ratingRequestPojo, @RequestHeader("X-RapidAPI-Key") String key){
        return ResponseEntity.ok(ratingService.save(ratingRequestPojo,key));
    }
}
