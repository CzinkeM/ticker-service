package com.epam.training.ticketservice.cli.command;


import org.springframework.shell.Availability;

public interface CommandAvailability {
    Availability isAvailable();
}
