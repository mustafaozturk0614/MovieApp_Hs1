package com.bilgeadam.service;

import com.bilgeadam.entity.Movie;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.MovieAppException;
import com.bilgeadam.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {


    private final MovieRepository movieRepository;

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> findAllByIds(List<Long> ids) {
       return ids.stream().map(this::findbyId).toList();
    }

    public Movie findbyId(long id) {
        Optional<Movie> movie=movieRepository.findById(id);
        if (movie.isEmpty()){
           throw  new MovieAppException(ErrorType.MOVIE_NOT_FOUND);
        }
        return  movie.get();
    }
  public  List<Movie> findAllByRatingOrderByRatingDesc(double rating){
        return movieRepository.findAllByRatingGreaterThan(rating);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}
