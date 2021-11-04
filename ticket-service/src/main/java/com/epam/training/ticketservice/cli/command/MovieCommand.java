package com.epam.training.ticketservice.cli.command;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.service.impl.MovieServiceImpl;
import com.epam.training.ticketservice.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class MovieCommand implements CommandAvailability {

    private final MovieServiceImpl movieService;
    private final UserServiceImpl userService;

    @Autowired
    public MovieCommand(MovieServiceImpl movieService, UserServiceImpl userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Create Movie", key = "create movie")
    public String createMovie(String title, String genre, int length) {
        return movieService.create(new Movie(title, genre, length));
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Update Movie", key = "update movie")
    public String updateMovie(String title, String genre, int length) {
        return movieService.update(new Movie(title, genre, length));
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Delete Movie", key = "delete movie")
    public String deleteMovie(String title) {
        return movieService.delete(title);
    }

    @ShellMethod(value = "List movies", key = "list movies")
    public String listMovies() {
        return movieService.getAll();
    }


    @Override
    public Availability isAvailable() {
        return userService.isCommandAvailable();
    }
}
