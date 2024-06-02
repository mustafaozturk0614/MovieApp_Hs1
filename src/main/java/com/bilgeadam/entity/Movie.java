package com.bilgeadam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<Genre> genres;
    private String language;
    private String image;
    private String name;
    private String country;
    private double rating;
    @Column(columnDefinition = "TEXT")
    private String summary;
    private LocalDate premiered;
    private String url;
    @OneToMany(mappedBy ="movie",cascade = CascadeType.ALL)
    private List<MovieComment> comments;


}
