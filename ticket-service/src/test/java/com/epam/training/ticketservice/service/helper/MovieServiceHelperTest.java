package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.models.Pair;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class MovieServiceHelperTest {


    private MovieServiceHelper underTest;
    private MovieRepository repository;

    @BeforeEach
    public void init(){
        repository = Mockito.mock(MovieRepository.class);
        underTest = new MovieServiceHelper(repository);
    }

    @Test
    public void isValidShouldThrowNullExceptionWhenMovieIsNull(){
        //Given
        Movie given = null;
        //When
        //Then
        Assertions.assertThrows(NullPointerException.class,() -> underTest.isParamsValid(given));
    }
    @Test
    public void isValidShouldThrowNullExceptionWhenMovieTitleIsNull(){
        //Given
        Movie given = new Movie(null, "sci-fi", 120);
        //When
        //Then
        Assertions.assertThrows(NullPointerException.class,() -> underTest.isParamsValid(given));
    }
    @Test
    public void isValidShouldThrowNullExceptionWhenMovieGenreIsNull(){
        //Given
        Movie given = new Movie("Star Wars", null, 120);
        //When
        //Then
        Assertions.assertThrows(NullPointerException.class,() -> underTest.isParamsValid(given));
    }
    @Test
    public void isValidShouldThrowNullExceptionWhenMovieTitleAndGenreIsNull(){
        //Given
        Movie given = new Movie(null, null, 120);
        //When
        //Then
        Assertions.assertThrows(NullPointerException.class,() -> underTest.isParamsValid(given));
    }
    @Test
    public void isValidShouldReturnWithPairOfFalseAndStringWhenMovieLengthIsUnderLowerLimit(){
        //Given
        Movie given = new Movie("Star Wars", "sci-fi", -1);
        Pair<Boolean, String> expected = new Pair<>(false, "Please provide valid length.");
        //When
        Pair<Boolean, String> actual = underTest.isParamsValid(given);
        //Then
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void isValidShouldReturnWithPairOfFalseAndStringWhenMovieLengthIsUnderUpperLimit(){
        //Given
        Movie given = new Movie("Star Wars", "sci-fi", 1261);
        Pair<Boolean, String> expected = new Pair<>(false, "Please provide valid length.");
        //When
        Pair<Boolean, String> actual = underTest.isParamsValid(given);
        //Then
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void isValidShouldReturnPairWithTrueAndStringWhenMovieIsCorrect(){
        //Given
        Movie given = new Movie("Star Wars", "sci-fi", 120);
        Pair<Boolean, String> expected = new Pair<>(true, "Its valid");
        //When
        Pair<Boolean, String> actual = underTest.isParamsValid(given);
        //Then
        Assertions.assertEquals(expected, actual);
    }

}
