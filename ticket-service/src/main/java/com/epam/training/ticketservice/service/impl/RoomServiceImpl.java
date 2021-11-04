package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.models.Room;
import com.epam.training.ticketservice.persistance.entity.RoomDto;
import com.epam.training.ticketservice.persistance.repository.RoomRepository;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.helper.MovieServiceHelper;
import com.epam.training.ticketservice.service.helper.RoomServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public String getAll() {
        var listOfRooms = roomRepository.findAll().stream().map(this::convertDtoToModel).collect(Collectors.toList());
        if (listOfRooms.isEmpty()) {
            return "There are no movies at the moment";
        }
        return new RoomServiceHelper().PrettyListString(listOfRooms);
    }

    @Override
    public String delete(String identifier) {
        return null;
    }

    @Override
    public String create(Room room) {
        var validator = new RoomServiceHelper().isValid(room);
        if (!validator.getFirst()) {
            return validator.getSecond();
        } else {
            roomRepository.save(convertModelToDto(room));
            return room.getName() + " CREATED";
        }
    }

    @Override
    public String update(Room room) {
        return null;
    }

    @Override
    public Room convertDtoToModel(RoomDto roomDto) {
        return new Room(roomDto.getName(), 10, 10);
    }

    @Override
    public RoomDto convertModelToDto(Room room) {
        return new RoomDto(null, room.getName(), room.getRows(), room.getColumns());
    }
}
