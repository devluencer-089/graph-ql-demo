package com.senacor.university.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.senacor.university.graphql.domain.Movie;
import com.senacor.university.graphql.domain.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class MovieQueryResolver implements GraphQLQueryResolver {

    private MovieRepository movieRepository;

    @Autowired
    public MovieQueryResolver(
            MovieRepository movieRepository
    ) {
        this.movieRepository = movieRepository;
    }

    public Movie findMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null);
    }

    public Iterable<Movie> allMovies() {
        return movieRepository.findAll();
    }
}
