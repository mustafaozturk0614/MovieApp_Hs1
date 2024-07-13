package com.bilgeadam.controller;

import com.bilgeadam.entity.Movie;
import com.bilgeadam.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("find-all-by-rating")
  public ResponseEntity<List<Movie>> findAllByRatingOrderByRatingDesc(double rating){
        return ResponseEntity.ok(movieService.findAllByRatingOrderByRatingDesc(rating));
    }
    @GetMapping("find-all")
    public ResponseEntity<List<Movie>> findAll(){
        return ResponseEntity.ok(movieService.findAll());
    }
}
