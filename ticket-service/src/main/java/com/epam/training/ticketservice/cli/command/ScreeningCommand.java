package com.epam.training.ticketservice.cli.command;

import com.epam.training.ticketservice.models.Screening;
import com.epam.training.ticketservice.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class ScreeningCommand {

    private final ScreeningService screeningService;

    @Autowired
    public ScreeningCommand(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @ShellMethod(value = "Create Screening", key = "create screening")
    public String createScreening(String movieName, String roomName, String  startDateString){
        Screening screening = new Screening(movieName,roomName,startDateString);
        return screeningService.create(screening);
    }
    @ShellMethod(value = "Delete Screening", key = "delete screening")
    public String deleteScreening(String movieTitle, String roomName, String startDateString){
        return screeningService.delete(startDateString);
    }
    @ShellMethod(value = "List Screening", key = "list screening")
    public String listScreening(){
        return screeningService.getAll();
    }
}
