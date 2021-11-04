package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.models.Room;
import com.epam.training.ticketservice.persistance.entity.RoomDto;
import com.epam.training.ticketservice.service.helper.GenericConverter;

public interface RoomService extends GenericService<Room>, GenericConverter<Room, RoomDto> {
}
