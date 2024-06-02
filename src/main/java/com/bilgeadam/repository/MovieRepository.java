package com.bilgeadam.repository;

import com.bilgeadam.entity.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie,Long> {


    List<Movie> findAllByRatingGreaterThan(double rating);
    List<Movie> findAllByRatingGreaterThanOrderByRatingDesc(double rating);
}
