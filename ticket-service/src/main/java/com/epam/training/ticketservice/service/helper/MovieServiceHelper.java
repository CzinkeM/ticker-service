package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.models.Pair;
import com.epam.training.ticketservice.models.Screening;
import com.epam.training.ticketservice.persistance.entity.MovieDto;
import com.epam.training.ticketservice.persistance.entity.ScreeningDto;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;

import java.util.List;
import java.util.Objects;

public class MovieServiceHelper implements ServiceHelper<Movie>, GenericConverter<Movie, MovieDto> {

    private final MovieRepository repository;

    public MovieServiceHelper(MovieRepository repository) {
        this.repository = repository;
    }

    public Movie getMovieByTitle(String movieName) {
        return repository.findByTitle(movieName).map(this::convertDtoToModel).orElse(null);
    }

    @Override
    public Pair<Boolean, String> isParamsValid(Movie movie) {
        Objects.requireNonNull(movie,"Movie cannot be null");
        Objects.requireNonNull(movie.getTitle(),"Movie cannot be null");
        Objects.requireNonNull(movie.getGenre(),"Movie cannot be null");
        if (1 > movie.getLength() || 1260 < movie.getLength()) {
            return new Pair<>(false, "Please provide valid length.");
        }
        return new Pair<>(true, "Its valid");
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        return new MovieDto(null, movie.getTitle(), movie.getGenre(), movie.getLength());
    }

    @Override
    public Movie convertDtoToModel(MovieDto movieDto) {
        return new Movie(movieDto.getTitle(), movieDto.getGenre(), movieDto.getLength());
    }
}
