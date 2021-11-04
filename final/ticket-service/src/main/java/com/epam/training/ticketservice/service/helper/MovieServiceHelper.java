package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.models.Pair;

import java.util.List;
import java.util.Objects;

public final class MovieServiceHelper {

    private MovieServiceHelper() {
    }

    public static Pair<Boolean, String> isMovieValid(Movie movieToValidate) {
        Objects.requireNonNull(movieToValidate, "Movie cannot be null");
        Objects.requireNonNull(movieToValidate.getTitle(), "Movie cannot be null");
        Objects.requireNonNull(movieToValidate.getGenre(), "Movie cannot be null");
        if (movieToValidate.getLength() <= 0 || movieToValidate.getLength() > 1260) {
            return new Pair<>(false, "Please provide valid length.");
        }
        if (Objects.equals(movieToValidate.getTitle(), "")) {
            return new Pair<>(false, "Please provide valid title.");
        }
        if (Objects.equals(movieToValidate.getGenre(), "")) {
            return new Pair<>(false, "Please provide valid genre.");
        }
        return new Pair<>(true, "Its valid");
    }

    public static String constructMovieListString(List<Movie> movies) {
        String moviesString = "";
        for (Movie movie : movies) {
            moviesString += movie.toString();
        }
        return moviesString;
    }
}
