package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.models.Pair;
import com.epam.training.ticketservice.persistance.entity.MovieDto;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.helper.GenericConverter;
import com.epam.training.ticketservice.service.helper.MovieServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MovieServiceImpl implements MovieService, GenericConverter<Movie, MovieDto> {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void init() {
        MovieDto starWars = new MovieDto(null, "Star Wars", "sci-fi", 120);
        MovieDto dune = new MovieDto(null, "Dűne", "sci-fi", 134);
        List<MovieDto> listOfMovies = Stream.of(starWars,dune).collect(Collectors.toList());
        movieRepository.saveAll(listOfMovies);
    }

    @Override
    public String getAll() {
        List<Movie> listOfMovies = movieRepository
                .findAll().stream()
                .map(this::convertDtoToModel)
                .collect(Collectors.toList());
        if (listOfMovies.isEmpty()) {
            return "There are no movies at the moment";
        }
        return new MovieServiceHelper().prettyListString(listOfMovies);
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
        Pair<Boolean, String> validator = new MovieServiceHelper().isValid(movie);
        if (!validator.getFirst()) {
            return validator.getSecond();
        } else {
            movieRepository.save(convertModelToDto(movie));
            return movie.getTitle() + " CREATED";
        }
    }

    @Override
    public String update(Movie movie) {
        Pair<Boolean, String>  validator = new MovieServiceHelper().isValid(movie);
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

    public Movie convertDtoToModel(MovieDto dto) {
        return new Movie(dto.getTitle(), dto.getGenre(), dto.getLength());
    }

    public MovieDto convertModelToDto(Movie model) {
        return new MovieDto(null, model.getTitle(), model.getGenre(), model.getLength());
    }
}
