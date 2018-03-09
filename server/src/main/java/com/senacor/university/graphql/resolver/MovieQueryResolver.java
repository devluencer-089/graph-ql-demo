package com.senacor.university.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.senacor.university.graphql.domain.Movie;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MovieQueryResolver implements GraphQLQueryResolver {

    public Movie movie() {
        return Movie.builder()
                .id(1L)
                .description("Zombies 4 Life")
                .title("The Walking Dead")
                .year(LocalDate.of(2017,1,1))
                .build();
    }
}
