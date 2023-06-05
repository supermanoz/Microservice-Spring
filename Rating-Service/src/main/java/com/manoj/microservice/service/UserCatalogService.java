package com.manoj.microservice.service;

import com.manoj.microservice.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserCatalogService {

    @Autowired
    WebClient.Builder webClientBuilder;

//    @HystrixCommand(defaultFallback = "getDefaultUser")
    public User getUser(Integer userID){
        User user=webClientBuilder.build()
                .get()
                .uri("http://user-app/users/fetch/"+userID)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        return user;
    }
//
//    public User getDefaultUser(Integer userID){
//        return null;
//    }
}
