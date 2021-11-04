package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Pair;
import com.epam.training.ticketservice.models.Room;

import java.util.List;
import java.util.Objects;

public class RoomServiceHelper implements ServiceHelper<Room> {

    @Override
    public Pair<Boolean, String> isValid(Room room) {
        Objects.requireNonNull(room, "Movie cannot be null");
        Objects.requireNonNull(room.getName(), "Movie cannot be null");
        return new Pair<>(true, "Its valid");
    }

    @Override
    public String PrettyListString(List<Room> movies) {
        return ServiceHelper.super.PrettyListString(movies);
    }
}
