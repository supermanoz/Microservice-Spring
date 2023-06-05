package com.manoj.microservice.service;

import com.manoj.microservice.RatingRepository;
import com.manoj.microservice.model.Movie;
import com.manoj.microservice.model.Rating;
import com.manoj.microservice.model.User;
import com.manoj.microservice.pojo.RatingRequestPojo;
import com.manoj.microservice.pojo.RatingResponsePojo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private MovieCatalogService movieCatalogService;
    @Autowired
    private UserCatalogService userCatalogService;
    @Autowired
    private WebClient.Builder webClientBuilder;
    public List<RatingResponsePojo> getRatings(String key){
        List<Rating> ratings= ratingRepository.findAll();
        List<RatingResponsePojo> response=new ArrayList<>();
        ratings.forEach(rating->{
            response.add(new RatingResponsePojo(rating.getId(),userCatalogService.getUser(rating.getUserID()),movieCatalogService.getMovie(rating.getImdbID(),key),rating.getRating()));
        });
        return response;
    }
    public Rating save(RatingRequestPojo ratingRequestPojo, String key){
        Rating rating=Rating.builder()
                .userID(ratingRequestPojo.getUserID())
                .imdbID(ratingRequestPojo.getImdbID())
                .rating(ratingRequestPojo.getRating())
                .build();
        return ratingRepository.save(rating);
    }
}
