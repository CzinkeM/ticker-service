package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.models.Pair;
import com.epam.training.ticketservice.persistance.entity.MovieDto;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.service.helper.MovieServiceHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.constraints.Null;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieServiceImplTest {

    private MovieServiceImpl underTest;
    private MovieRepository movieRepository;

    @BeforeEach
    public void init(){
        movieRepository = Mockito.mock(MovieRepository.class);
        underTest = new MovieServiceImpl(movieRepository);
    }
    @Test
    public void convertDtoToModelShouldReturnMovieWhenMovieDtoIsValid(){
        //Given
        MovieDto given = new MovieDto(null, "Star Wars", "sci-fi", 120);
        Movie expected = new Movie("Star Wars", "sci-fi", 120);
        //When
        Movie actual = underTest.convertDtoToModel(given);
        //Then
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void convertModelToDtoShouldReturnMovieDtoWhenMovieIsValid(){
        //Given
        Movie given = new Movie( "Star Wars", "sci-fi", 120);
        MovieDto expected = new MovieDto(null,"Star Wars", "sci-fi", 120);
        //When
        MovieDto actual = underTest.convertModelToDto(given);
        //Then
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenMovieIsNull(){
        Movie movie = null;
        Assertions.assertThrows(NullPointerException.class,()->underTest.update(movie));
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenMovieTitleIsNull(){
        Movie movie = new Movie(null, "sci-fi", 120);
        Assertions.assertThrows(NullPointerException.class,()->underTest.update(movie));
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenMovieGenreIsNull(){
        Movie movie = new Movie("Star Wars", null, 120);
        Assertions.assertThrows(NullPointerException.class,()->underTest.update(movie));
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenMovieIsCorrectButNotExisting(){
        Movie movie = new Movie("Star Wars", "sci-fi", 120);
        Mockito.when(movieRepository.findByTitle("Star Wars")).thenReturn(Optional.empty());
        String expected = "Cannot update a non-existing element";

        String actual = underTest.update(movie);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenMovieIsCorrectAndExisting(){
        Movie movie = new Movie("Star Wars", "sci-fi", 120);
        Mockito.when(movieRepository.findByTitle("Star Wars")).thenReturn(Optional.of(new MovieDto(null, "Star Wars", "sci-fi", 120)));
        String expected = "Star Wars UPDATED";

        String actual = underTest.update(movie);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenMovieLengthIsNegative(){
        Movie movie = new Movie("Star Wars", "sci-fi", -10);
        String expected = "Please provide valid length.";

        String actual = underTest.update(movie);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenMovieLengthIsZero(){
        Movie movie = new Movie("Star Wars", "sci-fi", 0);
        String expected = "Please provide valid length.";

        String actual = underTest.update(movie);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenMovieLengthIsBiggerThenUpperLimit(){
        Movie movie = new Movie("Star Wars", "sci-fi", 1261);
        String expected = "Please provide valid length.";

        String actual = underTest.update(movie);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void createShouldThrowsNullPointerExceptionWhenMovieIsNull(){
        Movie movie = null;

        Assertions.assertThrows(NullPointerException.class,() -> underTest.create(movie));
    }
    @Test
    public void createShouldThrowsNullPointerExceptionWhenMovieTitleIsNull(){
        Movie movie = new Movie(null, "sci-fi", 120);

        Assertions.assertThrows(NullPointerException.class,() -> underTest.create(movie));
    }
    @Test
    public void createShouldThrowsNullPointerExceptionWhenMovieGenreIsNull(){
        Movie movie = new Movie("Star Wars", null, 120);

        Assertions.assertThrows(NullPointerException.class,() -> underTest.create(movie));
    }
    @Test
    public void createShouldThrowsNullPointerExceptionWhenMovieIsValid(){
        Movie movie = new Movie("Star Wars", "sci-fi", 120);
        Mockito.when(movieRepository.save(new MovieDto(1, "Star Wars", "sci-fi", 120))).thenReturn(new MovieDto());
        String expected = "Star Wars CREATED";

        String actual = underTest.create(movie);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void createShouldReturnPairOfFalseAnsCorrespondingStringWhenLengthIsNegative(){
        Movie movie = new Movie("Star Wars", "sci-fi", -10);
        String expected = "Please provide valid length.";

        String actual = underTest.create(movie);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void createShouldReturnPairOfFalseAnsCorrespondingStringWhenLengthIsZero(){
        Movie movie = new Movie("Star Wars", "sci-fi", 0);
        String expected = "Please provide valid length.";

        String actual = underTest.create(movie);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void createShouldReturnPairOfFalseAnsCorrespondingStringWhenLengthIsBiggerThenUpperLimit(){
        Movie movie = new Movie("Star Wars", "sci-fi", 1261);
        String expected = "Please provide valid length.";

        String actual = underTest.create(movie);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void deleteShouldReturnCorrespondingStringWhenIdentifierIsNull(){
        //given
        String identifier = null;
        String expected = "Please provide valid identifier";
        //
        String actual = underTest.delete(identifier);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void deleteShouldReturnCorrespondingStringWhenIdentifierIsValidButMovieNotExist(){
        //given
        String identifier = "Star Wars";
        String expected =  "The movie with identifier: Star Wars does not exist.";
        Mockito.when(movieRepository.findByTitle("Star Wars")).thenReturn(Optional.empty());
        //
        String actual = underTest.delete(identifier);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void deleteShouldReturnCorrespondingStringWhenIdentifierIsValidAndExist(){
        //given
        String identifier = "Star Wars";
        String expected =  "Star Wars DELETED!";
        Mockito
                .when(movieRepository.findByTitle("Star Wars")).
                thenReturn(Optional.of(new MovieDto(null, "Star Wars", "sci-fi", 120)));
        //
        String actual = underTest.delete(identifier);

        Assertions.assertEquals(expected,actual);
    }
}
