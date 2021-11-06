package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.models.Room;
import com.epam.training.ticketservice.persistance.entity.RoomDto;
import com.epam.training.ticketservice.service.helper.GenericConverter;
import com.epam.training.ticketservice.service.helper.ListPrettier;

public interface RoomService extends GenericService<Room>, ListPrettier<Room> {
    boolean isRoomExist(String roomName);
}
