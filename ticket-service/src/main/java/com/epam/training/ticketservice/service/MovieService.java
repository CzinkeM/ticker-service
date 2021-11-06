package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.service.helper.ListPrettier;

public interface MovieService extends GenericService<Movie>, ListPrettier<Movie> {
    Movie getMovieByName(String movieName);

    boolean isMovieExist(String movieName);
}
