package com.epam.training.ticketservice.cli.command;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.persistance.entity.ScreeningDto;
import com.epam.training.ticketservice.service.helper.DateConverter;
import com.epam.training.ticketservice.models.Screening;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.text.ParseException;
import java.util.Date;

@ShellComponent
public class ScreeningCommand implements CommandAvailability {

    private final ScreeningService screeningService;
    private final MovieService movieService;
    private final UserService userService;

    @Autowired
    public ScreeningCommand(ScreeningService screeningService, MovieService movieService, UserService userService) {
        this.screeningService = screeningService;
        this.movieService = movieService;
        this.userService = userService;
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Create Screening", key = "create screening")
    public String createScreening(String movieName, String roomName, String  startDateString) {
        return screeningService.create(new ScreeningDto(null, movieName, roomName, startDateString));
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Delete Screening", key = "delete screening")
    public String deleteScreening(String movieTitle, String roomName, String startDateString) {
        return screeningService.delete(startDateString);
    }

    @ShellMethod(value = "List Screening", key = "list screenings")
    public String listScreening() {
        return screeningService.getAll();
    }

    @Override
    public Availability isAvailable() {
        return userService.isCommandAvailable();
    }
}
