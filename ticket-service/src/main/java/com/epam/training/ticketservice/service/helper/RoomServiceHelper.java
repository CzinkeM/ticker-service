package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.models.Pair;
import com.epam.training.ticketservice.models.Room;
import com.epam.training.ticketservice.persistance.entity.MovieDto;
import com.epam.training.ticketservice.persistance.entity.RoomDto;

import java.util.List;
import java.util.Objects;

public class RoomServiceHelper implements ServiceHelper<Room>, GenericConverter<Room, RoomDto> {

    @Override
    public Pair<Boolean, String> isParamsValid(Room room) {
        Objects.requireNonNull(room, "Movie cannot be null");
        Objects.requireNonNull(room.getName(), "Movie cannot be null");

        if (1 > room.getRows() || 100 < room.getRows()) {
            return new Pair<>(false, "Please provide valid row number.");
        }

        if (1 > room.getColumns() || 100 < room.getColumns()) {
            return new Pair<>(false, "Please provide valid column number.");
        }

        return new Pair<>(true, "Its valid");
    }

    @Override
    public Room convertDtoToModel(RoomDto dto) {
        return new Room(dto.getName(), dto.getRows(), dto.getColumn());
    }

    @Override
    public RoomDto convertModelToDto(Room model) {
        return new RoomDto(null, model.getName(), model.getRows(), model.getColumns());
    }
}
