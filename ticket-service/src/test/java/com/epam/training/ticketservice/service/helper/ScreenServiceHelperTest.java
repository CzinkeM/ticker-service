package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.models.Pair;
import com.epam.training.ticketservice.models.Screening;
import com.epam.training.ticketservice.persistance.entity.MovieDto;
import com.epam.training.ticketservice.persistance.entity.RoomDto;
import com.epam.training.ticketservice.persistance.entity.ScreeningDto;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.persistance.repository.RoomRepository;
import com.epam.training.ticketservice.persistance.repository.ScreeningRepository;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.util.List;
import java.util.Optional;

public class ScreenServiceHelperTest {
    private ScreenServiceHelper underTest;
    private MovieService movieService;
    private RoomService roomService;
    private ScreeningRepository screeningRepository;

    @BeforeEach
    public void init() {

        movieService = Mockito.mock(MovieService.class);
        roomService = Mockito.mock(RoomService.class);
        screeningRepository = Mockito.mock(ScreeningRepository.class);
        underTest = new ScreenServiceHelper(screeningRepository,roomService,movieService);
    }
    @Test
    public void convertDtoToModelShouldReturnScreeningWhenParameterIsValidScreeningDto(){
        var movie = new Movie("Toy Story", "Animation",98);
        var given = new ScreeningDto(null,"Toy Story","Room Tarantino", "2022-03-12 12:00");
        var expected = new Screening(movie, "Room Tarantino", "2022-03-12 12:00");
        Mockito.when(movieService.getMovieByName("Toy Story")).thenReturn(new Movie("Toy Story", "Animation",98));

        var actual = underTest.convertDtoToModel(given);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void convertModelToDtoShouldReturnScreeningDtoWhenParameterIsAValidScreening(){
        var movie = new Movie("Toy Story", "Animation",98);
        var expected = new ScreeningDto(null,"Toy Story","Room Tarantino", "2022-03-12 12:00");
        var given = new Screening(movie, "Room Tarantino", "2022-03-12 12:00");

        var actual = underTest.convertModelToDto(given);

        Assertions.assertEquals(expected,actual);
    }
}
