package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.persistance.entity.RoomDto;
import com.epam.training.ticketservice.persistance.entity.ScreeningDto;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.persistance.repository.RoomRepository;
import com.epam.training.ticketservice.persistance.repository.ScreeningRepository;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.service.helper.ScreenServiceHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.mock;

public class ScreenServiceImplTest {
    private ScreeningServiceImpl underTest;
    private MovieService movieService;
    private RoomService roomService;
    private ScreeningRepository screeningRepository;

    @BeforeEach
    public void init() {
        roomService = Mockito.mock(RoomService.class);
        movieService = Mockito.mock(MovieService.class);
        screeningRepository = Mockito.mock(ScreeningRepository.class);
        underTest = new ScreeningServiceImpl(screeningRepository,movieService,roomService);

    }

    @Test
    public void getAllShouldReturnCorrespondingStringWhenListIsEmpty(){
        //given
        String expected =  "There are no screenings";
        Mockito.when(screeningRepository.findAll()).thenReturn(List.of());
        //when
        String actual = underTest.getAll();
        //Then
        Assertions.assertEquals(expected,actual);
    }
}
