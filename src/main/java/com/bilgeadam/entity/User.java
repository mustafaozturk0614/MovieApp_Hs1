package com.bilgeadam.entity;

import com.bilgeadam.entity.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surName;
    private String email;
    private String password;
    private String phone;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Movie> favMovies;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Genre> favGenres;

    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL)
    private List<MovieComment> comments;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserType userType=UserType.USER;



}
