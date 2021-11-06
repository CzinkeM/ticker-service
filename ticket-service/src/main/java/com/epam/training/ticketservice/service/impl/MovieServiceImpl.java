package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.models.Pair;
import com.epam.training.ticketservice.persistance.entity.MovieDto;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.helper.ListPrettier;
import com.epam.training.ticketservice.service.helper.MovieServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieServiceHelper helper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        this.helper = new MovieServiceHelper(movieRepository);
    }

    @PostConstruct
    public void init() {
    }

    @Override
    public String getAll() {
        List<Movie> listOfMovies = helper.convertDtoListToModelList(movieRepository.findAll());
        if (listOfMovies.isEmpty()) {
            return "There are no movies at the moment";
        }
        return prettyListString(listOfMovies);
    }

    @Override
    public String delete(String identifier) {
        if (identifier == null) {
            return "Please provide valid identifier";
        }
        Optional<MovieDto> movieToDelete = movieRepository.findByTitle(identifier);

        if (movieToDelete.isPresent()) {
            movieRepository.delete(movieToDelete.get());
            return movieToDelete.get().getTitle() + " DELETED!";
        } else {
            return "The movie with identifier: " + identifier + " does not exist.";
        }

    }

    @Override
    public String create(Movie movie) {
        Pair<Boolean, String> validator = helper.isParamsValid(movie);
        if (!validator.getFirst()) {
            return validator.getSecond();
        } else {
            movieRepository.save(helper.convertModelToDto(movie));
            return movie.getTitle() + " CREATED";
        }
    }

    @Override
    public String update(Movie movie) {
        Pair<Boolean, String>  validator = helper.isParamsValid(movie);
        if (!validator.getFirst()) {
            return validator.getSecond();
        } else {
            //It uses the title as identifier
            Optional<MovieDto> movieToUpdate = movieRepository.findByTitle(movie.getTitle());
            if (movieToUpdate.isPresent()) {
                MovieDto updatedMovie = new MovieDto(
                        movieToUpdate.get().getId(),
                        movie.getTitle(),
                        movie.getGenre(),
                        movie.getLength());
                movieRepository.save(updatedMovie);
                return updatedMovie.getTitle() + " UPDATED";
            } else {
                return "Cannot update a non-existing element";
            }
        }
    }

    @Override
    public Movie getMovieByName(String nameOfMovie) {
        return helper.getMovieByTitle(nameOfMovie);
    }

    @Override
    public boolean isMovieExist(String movieName) {
        return movieRepository.findByTitle(movieName).isPresent();
    }


}
