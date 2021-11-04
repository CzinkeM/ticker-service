package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.models.Pair;

import java.util.List;
import java.util.Objects;

public class MovieServiceHelper implements ServiceHelper<Movie> {

    public MovieServiceHelper() {
    }

    @Override
    public Pair<Boolean, String> isValid(Movie movie) {
        Objects.requireNonNull(movie, "Movie cannot be null");
        Objects.requireNonNull(movie.getTitle(), "Movie cannot be null");
        Objects.requireNonNull(movie.getGenre(), "Movie cannot be null");
        if (movie.getLength() <= 0 || movie.getLength() > 1260) {
            return new Pair<>(false, "Please provide valid length.");
        }
        if (Objects.equals(movie.getTitle(), "")) {
            return new Pair<>(false, "Please provide valid title.");
        }
        if (Objects.equals(movie.getGenre(), "")) {
            return new Pair<>(false, "Please provide valid genre.");
        }
        return new Pair<>(true, "Its valid");
    }

    @Override
    public String prettyListString(List<Movie> movies) {
        return ServiceHelper.super.prettyListString(movies);
    }
}
