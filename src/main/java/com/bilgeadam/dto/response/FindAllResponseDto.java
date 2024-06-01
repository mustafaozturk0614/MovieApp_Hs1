package com.bilgeadam.dto.response;


import com.bilgeadam.entity.Genre;
import com.bilgeadam.entity.Movie;
import com.bilgeadam.entity.MovieComment;
import com.bilgeadam.entity.enums.UserType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindAllResponseDto {
    private Long id;
    private String name;
    private String surName;
    private String email;
    private String phone;
    private UserType userType;
    private List<Movie> favMovies;
    private List<Genre> favGenres;
    private List<MovieComment> comments;
}
