package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.persistance.entity.RoomDto;
import com.epam.training.ticketservice.persistance.entity.ScreeningDto;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.persistance.repository.RoomRepository;
import com.epam.training.ticketservice.persistance.repository.ScreeningRepository;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.service.helper.ScreenServiceHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ScreenServiceImplTest {
    private ScreeningServiceImpl underTest;
    private MovieRepository movieRepository;
    private RoomRepository roomRepository;
    private ScreeningRepository screeningRepository;

    @BeforeEach
    public void init() {
        movieRepository = Mockito.mock(MovieRepository.class);
        roomRepository = Mockito.mock(RoomRepository.class);
        screeningRepository = Mockito.mock(ScreeningRepository.class);
        underTest = new ScreeningServiceImpl(screeningRepository,movieRepository,roomRepository);

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
