package com.manoj.microservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="rating")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userID;
    private String imdbID;
    private Integer rating;
}
