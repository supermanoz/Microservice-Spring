package com.manoj.microservice.pojo;

import com.manoj.microservice.model.Movie;
import com.manoj.microservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponsePojo {
    private Integer id;
    private User user;
    private Movie movie;
    private Integer rating;
}
