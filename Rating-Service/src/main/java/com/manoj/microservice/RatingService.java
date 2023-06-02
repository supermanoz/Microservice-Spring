package com.manoj.microservice;

import com.manoj.microservice.model.Movie;
import com.manoj.microservice.model.Rating;
import com.manoj.microservice.model.User;
import com.manoj.microservice.pojo.RatingRequestPojo;
import com.manoj.microservice.pojo.RatingResponsePojo;
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
    private WebClient.Builder webClientBuilder;
    public List<RatingResponsePojo> getRatings(String key){
        List<Rating> ratings= ratingRepository.findAll();
        List<RatingResponsePojo> response=new ArrayList<>();
        ratings.forEach(rating->{
            response.add(new RatingResponsePojo(rating.getId(),getUser(rating.getUserID()),getMovie(rating.getImdbID(),key),rating.getRating()));
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

    public User getUser(Integer userID){
        User user=webClientBuilder.build()
                .get()
                .uri("http://user-app/users/fetch/"+userID)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        return user;
    }

    public Movie getMovie(String imdbID,String key){
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
}
