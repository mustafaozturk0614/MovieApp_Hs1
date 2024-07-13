package com.bilgeadam.utility;


import com.bilgeadam.entity.Movie;
import com.bilgeadam.entity.MovieComment;
import com.bilgeadam.entity.User;
import com.bilgeadam.entity.enums.UserType;
import com.bilgeadam.service.GenreService;
import com.bilgeadam.service.MovieService;
import com.bilgeadam.service.UserService;
import com.bilgeadam.utility.data.Sample;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataImpl  implements ApplicationRunner {
    private final MovieService movieService;
    private final UserService userService;
    private final GenreService genreService;
    @Override
    public void run(ApplicationArguments args) throws Exception {

      getAllMovies();
      createUser();
    }


    public  void getAllMovies(){
        try {
            URL url= new URL("https://api.tvmaze.com/shows");
            HttpURLConnection hr= (HttpURLConnection) url.openConnection();
            InputStream inputStream=hr.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String value="";
            value=bufferedReader.readLine();
            Sample[] array=new Gson().fromJson(value,Sample[].class);
             Arrays.asList(array).forEach(x->{

                 Movie movie=null;
                 if(x.network==null){
                     movie= Movie.builder()
                             .id(x.id)
                             .url(x.url)
                             .image(x.image.medium)
                             .language(x.language)
                             .premiered(LocalDate.parse(x.premiered))
                             .summary(x.summary)
                             .name(x.name)
                             .genres(genreService.createGenresWithNames(x.genres))
                             .rating(x.rating.average)
                             .build();
                 }else{
                     movie= Movie.builder()
                             .id(x.id)
                             .url(x.url)
                             .image(x.image.medium)
                             .language(x.language)
                             .premiered(LocalDate.parse(x.premiered))
                             .summary(x.summary)
                             .name(x.name)
                             .rating(x.rating.average)
                             .country(x.network.country.name)
                             .genres(genreService.createGenresWithNames(x.genres))
                             .build();
                 }

                 movieService.save(movie);
             });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void createUser(){

        User user = User.builder().email("mert@hotmail.com").name("Mert").
                surName("Kaya").password("123").phone("123")
                .favGenres(genreService.createGenresWithNames(List.of("Drama", "Science-Fiction", "Horror","Documentry")))
                .favMovies(movieService.findAllByIds(List.of(1l, 10L, 15L, 100L, 50L, 90L, 120L, 148L)))
                .build();


        User user1 = User.builder().email("merve@gmail.com").name("Merve")
                .favGenres(genreService.createGenresWithNames(List.of("Drama", "Action", "Romance")))
                .favMovies(movieService.findAllByIds(List.of(8l, 3L, 17L, 18L, 9L, 85L, 78L, 127L, 1L, 120L, 85L)))
                .surName("Ozturk").password("123").phone("123")
                .build();

        user1.setComments(List.of(
                MovieComment.builder().content("iyi").date(LocalDate.now().minusYears(1)).user(user1).movie(movieService.findbyId(78L)).build(),
                MovieComment.builder().content("iyi").date(LocalDate.now().minusMonths(3)).user(user1).movie(movieService.findbyId(120L)).build(),
                MovieComment.builder().content("cok iyi").date(LocalDate.now().minusWeeks(2)).user(user1).movie(movieService.findbyId(1L)).build(),
                MovieComment.builder().content("idare eder").date(LocalDate.now().minusDays(4)).user(user1).movie(movieService.findbyId(136L)).build(),
                MovieComment.builder().content("iyi").date(LocalDate.now().minusDays(2)).user(user1).movie(movieService.findbyId(85)).build()
        ));

        User user2 = User.builder().email("hasan@yandex.com").name("Hasan").

                surName("Bayindir").password("123").phone("123")
                .favGenres(genreService.createGenresWithNames(List.of("War", "Western", "History", "Action")))
                .favMovies(movieService.findAllByIds(List.of(152l, 2L, 5L, 88L, 120L, 3L, 120L, 150L, 16L, 25L, 38L, 78L, 96L, 136L)))
                .build();
        user2.setComments((List.of(
                MovieComment.builder().content("iyi").date(LocalDate.now().minusMonths(8)).user(user2).movie(movieService.findbyId(2L)).build(),
                MovieComment.builder().content("iyi").date(LocalDate.now().minusMonths(5)).user(user2).movie(movieService.findbyId(136L)).build(),
                MovieComment.builder().content("cok iyi").date(LocalDate.now().minusMonths(4)).user(user2).movie(movieService.findbyId(106L)).build(),
                MovieComment.builder().content("idare eder").date(LocalDate.now().minusWeeks(10)).user(user2).movie(movieService.findbyId(26L)).build(),
                MovieComment.builder().content("iyi").date(LocalDate.now().minusWeeks(2)).user(user2).movie(movieService.findbyId(25)).build()
        )));
        User user3 = User.builder().email("aras@gmail.com")
                .favGenres(genreService.createGenresWithNames(List.of("Anime", "Comedy", "Supernatural", "Action")))
                .favMovies(movieService.findAllByIds(List.of(15l, 2L, 1L, 86L, 39L, 32L, 200L, 15L, 106L, 205L, 138L, 48L, 64L, 136L)))
                .name("Aras").
                surName("Gr").password("123").phone("123").favGenres(genreService.createGenresWithNames(List.of("Anime", "Comedy", "Supernatural", "Action"))).build();
        user3.setComments(List.of(
                MovieComment.builder().content("berbat").date(LocalDate.now().minusWeeks(58)).user(user3).movie(movieService.findbyId(14L)).build(),
                MovieComment.builder().content("iyi").user(user3).date(LocalDate.now().minusWeeks(45)).movie(movieService.findbyId(136L)).build(),
                MovieComment.builder().content("cok iyi").date(LocalDate.now().minusMonths(2)).user(user3).movie(movieService.findbyId(106L)).build(),
                MovieComment.builder().content("berbat").date(LocalDate.now().minusWeeks(3)).user(user3).movie(movieService.findbyId(10L)).build(),
                MovieComment.builder().content("iyi").user(user3).date(LocalDate.now().minusDays(9)).movie(movieService.findbyId(15)).build()
        ));

        User user4 = User.builder()
                .favMovies(movieService.findAllByIds(List.of(51l, 212L, 81L, 86L, 139L, 52L, 20L, 105L, 126L, 25L, 18L, 4L, 6L, 126L)))
                .email("didem@gmail.com").name("Didem")
                .surName("Sonmez").password("123").phone("123")
                .favGenres(genreService.createGenresWithNames(List.of("Anime", "Action", "Mystery", "Supernatural"))).build();

        user4.setComments(List.of(
                MovieComment.builder().content("cok iyi").date(LocalDate.now().minusWeeks(3)).user(user4).movie(movieService.findbyId(51L)).build(),
                MovieComment.builder().content("�ahane").date(LocalDate.now().minusDays(12)).user(user4).movie(movieService.findbyId(105L)).build(),
                MovieComment.builder().content("idare eder").date(LocalDate.now().minusDays(3)).user(user4).movie(movieService.findbyId(1L)).build()
        ));

        User user5 = User.builder().email("admin@gmail.com").name("Mustafa").
                surName("Ozturk").userType(UserType.ADMIN).password("admin").phone("123")
                .favGenres(genreService.createGenresWithNames(List.of("Science-Fiction", "Drama", "Music", "Anime")))
                .favMovies(movieService.findAllByIds(List.of(14l, 22L, 106L, 88L, 104L, 66L, 20L, 104L, 126L, 25L, 13L, 4L, 69L, 47L, 200L)))

                .build();

        user5.setComments(List.of(
                MovieComment.builder().content("cok iyi").date(LocalDate.now().minusWeeks(50)).user(user5).movie(movieService.findbyId(14L)).build(),
                MovieComment.builder().content("iyi").date(LocalDate.now().minusMonths(10)).user(user5).movie(movieService.findbyId(66L)).build(),
                MovieComment.builder().content("berbat").date(LocalDate.now().minusDays(50)).user(user5).movie(movieService.findbyId(10L)).build(),
                MovieComment.builder().content("berbat").date(LocalDate.now().minusWeeks(5)).user(user5).movie(movieService.findbyId(108L)).build(),
                MovieComment.builder().content("iyi").date(LocalDate.now().minusDays(10)).user(user5).movie(movieService.findbyId(25)).build()
        ));
        userService.saveAll(List.of(user, user1, user2, user3, user4, user5));

    }

}
