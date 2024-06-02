package com.bilgeadam.service;

import com.bilgeadam.entity.Genre;
import com.bilgeadam.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<Genre> createGenresWithNames(List<String> genres) { /// Drama Anime Aksiyon
        List<Genre> genreList=new ArrayList<>();
        for (String genreName : genres) {
            Optional<Genre> genre = genreRepository.findOptionalByName(genreName); //<Drama>
            if (genre.isPresent()){
                genreList.add(genre.get());
            }else{
                Genre myGenre= Genre.builder().name(genreName).build();
                genreRepository.save(myGenre);
                genreList.add(myGenre);
            }
        }
        return genreList;
    }
}
