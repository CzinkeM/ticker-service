package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.models.Pair;
import com.epam.training.ticketservice.models.Room;
import com.epam.training.ticketservice.persistance.entity.MovieDto;
import com.epam.training.ticketservice.persistance.entity.RoomDto;
import com.epam.training.ticketservice.persistance.repository.RoomRepository;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.helper.MovieServiceHelper;
import com.epam.training.ticketservice.service.helper.RoomServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @PostConstruct
    public void init() {
        RoomDto roomDto = new RoomDto(null, "room-1", 10, 10);
        roomRepository.save(roomDto);
    }

    @Override
    public String getAll() {
        List<Room> listOfRooms = roomRepository
                .findAll()
                .stream()
                .map(this::convertDtoToModel)
                .collect(Collectors.toList());
        if (listOfRooms.isEmpty()) {
            return "There are no movies at the moment";
        }
        return new RoomServiceHelper().prettyListString(listOfRooms);
    }

    @Override
    public String delete(String identifier) {
        if (identifier == null) {
            return "Please provide valid identifier";
        }
        Optional<RoomDto> roomToDelete = roomRepository.findByName(identifier);

        if (roomToDelete.isPresent()) {
            roomRepository.delete(roomToDelete.get());
            return roomToDelete.get().getName() + " DELETED!";
        } else {
            return "The room with identifier: " + identifier + " does not exist.";
        }
    }

    @Override
    public String create(Room room) {
        Pair<Boolean, String> validator = new RoomServiceHelper().isValid(room);
        if (!validator.getFirst()) {
            return validator.getSecond();
        } else {
            roomRepository.save(convertModelToDto(room));
            return room.getName() + " CREATED";
        }
    }

    @Override
    public String update(Room room) {
        Pair<Boolean, String>  validator = new RoomServiceHelper().isValid(room);
        if (!validator.getFirst()) {
            return validator.getSecond();
        } else {
            //It uses the title as identifier
            Optional<RoomDto> roomToUpdate = roomRepository.findByName(room.getName());
            if (roomToUpdate.isPresent()) {
                RoomDto updatedMovie = new RoomDto(
                        roomToUpdate.get().getId(),
                        room.getName(),
                        room.getRows(),
                        room.getColumns());
                roomRepository.save(updatedMovie);
                return updatedMovie.getName() + " UPDATED";
            } else {
                return "Cannot update a non-existing element";
            }
        }
    }

    @Override
    public Room convertDtoToModel(RoomDto roomDto) {
        return new Room(roomDto.getName(), roomDto.getRows(), roomDto.getColumn());
    }

    @Override
    public RoomDto convertModelToDto(Room room) {
        return new RoomDto(null, room.getName(), room.getRows(), room.getColumns());
    }
}
