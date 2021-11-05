package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.models.Room;
import com.epam.training.ticketservice.persistance.entity.MovieDto;
import com.epam.training.ticketservice.persistance.entity.RoomDto;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.persistance.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

public class RoomServiceImplTest {

    private RoomServiceImpl underTest;
    private RoomRepository roomRepository;

    @BeforeEach
    public void init(){
        roomRepository = Mockito.mock(RoomRepository.class);
        underTest = new RoomServiceImpl(roomRepository);
    }

    @Test
    public void getAllShouldReturnCorrespondingStringWhenListIsEmpty(){
        //given
        String expected =  "There are no movies at the moment";
        Mockito.when(roomRepository.findAll()).thenReturn(List.of());
        //when
        String actual = underTest.getAll();
        //Then
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void getAllShouldReturnCorrespondingStringWhenListNotEmpty(){
        //given
        String expected = "Room 1 with 1 seats, 1 rows and 1 columns\nRoom 2 with 4 seats, 2 rows and 2 columns\n";
        RoomDto room1 = new RoomDto(null,"1", 1, 1);
        RoomDto room2 = new RoomDto(null,"2", 2, 2);
        Mockito.when(roomRepository.findAll()).thenReturn(List.of(room1, room2));
        //when
        String actual = underTest.getAll();
        //Then
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void createShouldThrowNullPointerExceptionWhenRoomIsNull(){
        Room room = null;

        Assertions.assertThrows(NullPointerException.class,() -> underTest.create(room));
    }
    @Test
    public void createShouldThrowNullPointerExceptionWhenRoomNameIsNull(){
        Room room = new Room(null, 10, 20);

        Assertions.assertThrows(NullPointerException.class,() -> underTest.create(room));
    }
    @Test
    public void createShouldThrowNullPointerExceptionWhenRoomRowsIsNegative(){
        Room room = new Room("1",-1, 10);
        String expected = "Please provide valid row number.";

        String actual = underTest.create(room);

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void createShouldThrowNullPointerExceptionWhenRoomRowsIsZero(){
        Room room = new Room("1",0, 10);
        String expected = "Please provide valid row number.";

        String actual = underTest.create(room);

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void createShouldThrowNullPointerExceptionWhenRoomRowsIsBiggerThenUpperLimit(){
        Room room = new Room("1",101, 10);
        String expected = "Please provide valid row number.";

        String actual = underTest.create(room);

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void createShouldThrowNullPointerExceptionWhenRoomColumnIsBiggerThenUpperLimit(){
        Room room = new Room("1",10, 101);
        String expected = "Please provide valid column number.";

        String actual = underTest.create(room);

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void createShouldThrowNullPointerExceptionWhenRoomColumnIsNegative(){
        Room room = new Room("1",10, -1);
        String expected = "Please provide valid column number.";

        String actual = underTest.create(room);

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void createShouldThrowNullPointerExceptionWhenRoomColumnIsZero(){
        Room room = new Room("1",10, 0);
        String expected = "Please provide valid column number.";

        String actual = underTest.create(room);

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void deleteShouldReturnCorrespondingStringWhenIdentifierIsNull(){
        String identifier = null;
        String expected = "Please provide valid identifier";

        String actual = underTest.delete(identifier);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void deleteShouldReturnCorrespondingStringWhenIdentifierIsNotNullButNotExist(){
        String identifier = "1";
        String expected = "The room with identifier: 1 does not exist.";
        Mockito.when(roomRepository.findByName(identifier)).thenReturn(Optional.empty());

        String actual = underTest.delete(identifier);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void deleteShouldReturnCorrespondingStringWhenIdentifierIsNotNullAndExist(){
        String identifier = "1";
        String expected = "1 DELETED!";
        Mockito.when(roomRepository.findByName(identifier)).thenReturn(Optional.of(new RoomDto(null, "1", 10 ,10)));

        String actual = underTest.delete(identifier);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenRoomIsNull(){
        Room room = null;
        Assertions.assertThrows(NullPointerException.class,()->underTest.update(room));
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenRoomNameIsNull(){
        Room room = new Room(null, 10, 10);
        Assertions.assertThrows(NullPointerException.class,()->underTest.update(room));
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenRoomRowNegative(){
        Room room = new Room("1", -1, 10);
        String expected = "Please provide valid row number.";

        String actual = underTest.update(room);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenRoomRowZero(){
        Room room = new Room("1", 0, 10);
        String expected = "Please provide valid row number.";

        String actual = underTest.update(room);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenRoomRowIsBiggerThenUpperLimit(){
        Room room = new Room("1", 101, 10);
        String expected = "Please provide valid row number.";

        String actual = underTest.update(room);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenMovieIsCorrectButNotExisting(){
        Room room = new Room("1", 10, 10);
        String expected = "Cannot update a non-existing element";
        Mockito.when(roomRepository.findByName("1")).thenReturn(Optional.empty());

        String actual = underTest.update(room);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void updateShouldReturnCorrespondingStringWhenMovieIsCorrectAndExist(){
        Room room = new Room("1", 10, 10);
        String expected = "1 UPDATED";
        Mockito.when(roomRepository.findByName("1")).thenReturn(Optional.of(new RoomDto(null, "1", 10, 12)));

        String actual = underTest.update(room);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void convertDtoToModelShouldReturnRoomWhenRoomDtoIsValid(){
        //Given
        RoomDto given = new RoomDto(null, "1", 10, 12);
        Room expected = new Room("1", 10, 12);
        //When
        Room actual = underTest.convertDtoToModel(given);
        //Then
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void convertModelToDtoShouldReturnRoomDtoWhenRoomIsValid(){
        //Given
        Room given =new Room("1", 10, 12);
        RoomDto expected = new RoomDto(null, "1", 10, 12);
        //When
        RoomDto actual = underTest.convertModelToDto(given);
        //Then
        Assertions.assertEquals(expected, actual);
    }
}
