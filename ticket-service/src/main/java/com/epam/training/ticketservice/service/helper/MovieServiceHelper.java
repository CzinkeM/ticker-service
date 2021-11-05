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
        int movieLength = movie.getLength();
        Objects.requireNonNull(movie, "Movie cannot be null");
        Objects.requireNonNull(movie.getTitle(), "Movie cannot be null");
        Objects.requireNonNull(movie.getGenre(), "Movie cannot be null");
        if (1 > movieLength || 1260 < movieLength) {
            return new Pair<>(false, "Please provide valid length.");
        }
        return new Pair<>(true, "Its valid");
    }

    @Override
    public String prettyListString(List<Movie> movies) {
        return ServiceHelper.super.prettyListString(movies);
    }
}
