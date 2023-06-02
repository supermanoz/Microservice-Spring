package com.manoj.microservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingRequestPojo {
    private Integer userID;
    private String imdbID;
    private Integer rating;
}
