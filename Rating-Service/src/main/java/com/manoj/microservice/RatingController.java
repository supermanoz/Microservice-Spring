package com.manoj.microservice;

import com.manoj.microservice.pojo.RatingRequestPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @GetMapping("/fetchAll")
    public ResponseEntity<?> getRatings(@RequestHeader("X-RapidAPI-Key") String key){
        return ResponseEntity.ok(ratingService.getRatings(key));
    }

    @PostMapping("/save")
    public ResponseEntity<?> addRating(@RequestBody RatingRequestPojo ratingRequestPojo, @RequestHeader("X-RapidAPI-Key") String key){
        return ResponseEntity.ok(ratingService.save(ratingRequestPojo,key));
    }
}
