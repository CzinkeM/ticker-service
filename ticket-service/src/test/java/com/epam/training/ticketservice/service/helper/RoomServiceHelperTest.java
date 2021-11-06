package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Pair;
import com.epam.training.ticketservice.models.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RoomServiceHelperTest {
    private RoomServiceHelper underTest;

    @BeforeEach
    public void init(){
        underTest = new RoomServiceHelper();
    }

    @Test
    public void isValidShouldThrowNullExceptionWhenMovieIsNull(){
        //Given
        Room given = null;
        //When
        //Then
        Assertions.assertThrows(NullPointerException.class,() -> underTest.isParamsValid(given));
    }
    @Test
    public void isValidShouldThrowNullExceptionWhenMovieTitleIsNull(){
        //Given
        Room given = new Room(null, 10, 10);
        //When
        //Then
        Assertions.assertThrows(NullPointerException.class,() -> underTest.isParamsValid(given));
    }
    @Test
    public void isValidShouldReturnPairWhenMovieIsCorrect(){
        //Given
        Room given = new Room("Tarantino", 10, 10);
        Pair<Boolean, String> expected = new Pair<>(true, "Its valid");
        //When
        Pair<Boolean, String> actual = underTest.isParamsValid(given);
        //Then
        Assertions.assertEquals(expected, actual);
    }
}
