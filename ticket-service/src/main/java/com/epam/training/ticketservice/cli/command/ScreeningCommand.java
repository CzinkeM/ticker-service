package com.epam.training.ticketservice.cli.command;

import com.epam.training.ticketservice.models.Screening;
import com.epam.training.ticketservice.models.User;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class ScreeningCommand implements CommandAvailability {

    private final ScreeningService screeningService;
    private final UserService userService;

    @Autowired
    public ScreeningCommand(ScreeningService screeningService, UserService userService) {
        this.screeningService = screeningService;
        this.userService = userService;
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Create Screening", key = "create screening")
    public String createScreening(String movieName, String roomName, String  startDateString) {
        Screening screening = new Screening(movieName,roomName,startDateString);
        return screeningService.create(screening);
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Delete Screening", key = "delete screening")
    public String deleteScreening(String movieTitle, String roomName, String startDateString) {
        return screeningService.delete(startDateString);
    }

    @ShellMethod(value = "List Screening", key = "list screening")
    public String listScreening() {
        return screeningService.getAll();
    }

    @Override
    public Availability isAvailable() {
        return userService.isCommandAvailable();
    }
}
