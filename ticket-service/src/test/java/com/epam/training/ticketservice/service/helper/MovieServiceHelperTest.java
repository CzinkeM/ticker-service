package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.models.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MovieServiceHelperTest {


    private MovieServiceHelper underTest;

    @BeforeEach
    public void init(){
        underTest = new MovieServiceHelper();
    }

    @Test
    public void isValidShouldThrowNullExceptionWhenMovieIsNull(){
        //Given
        Movie given = null;
        //When
        //Then
        Assertions.assertThrows(NullPointerException.class,() -> underTest.isValid(given));
    }
    @Test
    public void isValidShouldThrowNullExceptionWhenMovieTitleIsNull(){
        //Given
        Movie given = new Movie(null, "sci-fi", 120);
        //When
        //Then
        Assertions.assertThrows(NullPointerException.class,() -> underTest.isValid(given));
    }
    @Test
    public void isValidShouldThrowNullExceptionWhenMovieGenreIsNull(){
        //Given
        Movie given = new Movie("Star Wars", null, 120);
        //When
        //Then
        Assertions.assertThrows(NullPointerException.class,() -> underTest.isValid(given));
    }
    @Test
    public void isValidShouldThrowNullExceptionWhenMovieTitleAndGenreIsNull(){
        //Given
        Movie given = new Movie(null, null, 120);
        //When
        //Then
        Assertions.assertThrows(NullPointerException.class,() -> underTest.isValid(given));
    }
    @Test
    public void isValidShouldReturnWithPairOfFalseAndStringWhenMovieLengthIsUnderLowerLimit(){
        //Given
        Movie given = new Movie("Star Wars", "sci-fi", -1);
        Pair<Boolean, String> expected = new Pair<>(false, "Please provide valid length.");
        //When
        Pair<Boolean, String> actual = underTest.isValid(given);
        //Then
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void isValidShouldReturnWithPairOfFalseAndStringWhenMovieLengthIsUnderUpperLimit(){
        //Given
        Movie given = new Movie("Star Wars", "sci-fi", 1261);
        Pair<Boolean, String> expected = new Pair<>(false, "Please provide valid length.");
        //When
        Pair<Boolean, String> actual = underTest.isValid(given);
        //Then
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void isValidShouldReturnPairWithTrueAndStringWhenMovieIsCorrect(){
        //Given
        Movie given = new Movie("Star Wars", "sci-fi", 120);
        Pair<Boolean, String> expected = new Pair<>(true, "Its valid");
        //When
        Pair<Boolean, String> actual = underTest.isValid(given);
        //Then
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void prettyListStringShouldReturnStringWhenParameterIsCorrect(){
        //Given
        Movie testMovie1 = new Movie("Star Wars", "sci-fi", 120);
        Movie testMovie2 = new Movie("Dűne", "sci-fi", 133);
        List<Movie> testMovieList = List.of(testMovie1,testMovie2);
        String expected = "Star Wars ( sci-fi, 120 minutes)\nDűne ( sci-fi, 133 minutes)\n";
        //When
        String actual = underTest.prettyListString(testMovieList);
        //Then
        Assertions.assertEquals(expected, actual);
    }

}
