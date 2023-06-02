package com.manoj.microservice;

import com.manoj.microservice.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,String> {
}
