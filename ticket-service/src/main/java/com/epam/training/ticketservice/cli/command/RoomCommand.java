package com.epam.training.ticketservice.cli.command;

import com.epam.training.ticketservice.models.Room;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class RoomCommand implements CommandAvailability {

    private final RoomService roomService;
    private final UserService userService;

    @Autowired
    public RoomCommand(RoomService roomService, UserService userService) {
        this.roomService = roomService;
        this.userService = userService;
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Create Room", key = "create room")
    public String createRoom(String name, int row, int column) {
        return roomService.create(new Room(name, row, column));
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Update Room", key = "update room")
    public String updateRoom(String name, int row, int column) {
        return roomService.update(new Room(name, row, column));
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Delete Room", key = "delete room")
    public String deleteRoom(String title) {
        return roomService.delete(title);
    }

    @ShellMethod(value = "List Rooms", key = "list rooms")
    public String listRooms() {
        return roomService.getAll();
    }

    @Override
    public Availability isAvailable() {
        return userService.isCommandAvailable();
    }
}
