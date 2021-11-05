package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Movie;
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
        Assertions.assertThrows(NullPointerException.class,() -> underTest.isValid(given));
    }
    @Test
    public void isValidShouldThrowNullExceptionWhenMovieTitleIsNull(){
        //Given
        Room given = new Room(null, 10, 10);
        //When
        //Then
        Assertions.assertThrows(NullPointerException.class,() -> underTest.isValid(given));
    }
    @Test
    public void isValidShouldReturnPairWhenMovieIsCorrect(){
        //Given
        Room given = new Room("Tarantino", 10, 10);
        Pair<Boolean, String> expected = new Pair<>(true, "Its valid");
        //When
        Pair<Boolean, String> actual = underTest.isValid(given);
        //Then
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void prettyListStringShouldReturnStringWhenParameterIsCorrect(){
        //Given
        Room testRoom1 = new Room("Tarantino", 10, 20);
        Room testRoom2 = new Room("Scorsese", 30, 25);
        List<Room> testRoomList = List.of(testRoom1,testRoom2);
        String expected = "Room Tarantino with 200 seats, 10 rows and 20 columns\n" +
                "Room Scorsese with 750 seats, 30 rows and 25 columns\n";
        //When
        String actual = underTest.prettyListString(testRoomList);
        //Then
        Assertions.assertEquals(expected, actual);
    }
}
