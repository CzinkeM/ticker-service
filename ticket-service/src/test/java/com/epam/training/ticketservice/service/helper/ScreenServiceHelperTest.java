package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.models.Screening;
import com.epam.training.ticketservice.persistance.entity.MovieDto;
import com.epam.training.ticketservice.persistance.entity.RoomDto;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.persistance.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Optional;

public class ScreenServiceHelperTest {
    private ScreenServiceHelper underTest;
    private MovieRepository movieRepository;
    private RoomRepository roomRepository;

    @BeforeEach
    public void init() {
        underTest = new ScreenServiceHelper();
        movieRepository = Mockito.mock(MovieRepository.class);
        roomRepository = Mockito.mock(RoomRepository.class);
    }

    @Test
    public void isMovieValidShouldReturnFalseWhenMovieDoesNotExist() {
        String givenTitle = "Spiderman";
        Mockito.when(movieRepository.findByTitle("Spiderman")).thenReturn(Optional.empty());

        boolean actual = underTest.isMovieValid(movieRepository,givenTitle);

        Assertions.assertFalse(actual);
    }
    @Test
    public void isMovieValidShouldReturnTrueWhenMovieExist(){
        String givenTitle = "Spiderman";
        Mockito.when(movieRepository.findByTitle("Spiderman"))
                .thenReturn(Optional.of(new MovieDto(null,"Spiderman","sci-fi",120 )));

        boolean actual = underTest.isMovieValid(movieRepository,givenTitle);

        Assertions.assertTrue(actual);
    }
    @Test
    public void isRoomValidShouldReturnFalseWhenMovieDoesNotExist() {
        String givenTitle = "panic-room";
        Mockito.when(roomRepository.findByName("panic-room")).thenReturn(Optional.empty());

        boolean actual = underTest.isRoomValid(roomRepository,givenTitle);

        Assertions.assertFalse(actual);
    }
    @Test
    public void isRoomValidShouldReturnTrueWhenRoomExist(){
        String givenTitle = "panic-room";
        Mockito.when(roomRepository.findByName("panic-room"))
                .thenReturn(Optional.of(new RoomDto(null,"panic-room",10,10 )));

        boolean actual = underTest.isRoomValid(roomRepository,givenTitle);

        Assertions.assertTrue(actual);
    }
}
