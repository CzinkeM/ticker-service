package com.epam.training.ticketservice.service;

import org.springframework.shell.Availability;

public interface UserService {
    String signIn(String username, String password);

    String signOut();

    String describeAccount();

    Availability isCommandAvailable();
}
