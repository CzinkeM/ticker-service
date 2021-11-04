package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.models.User;
import com.epam.training.ticketservice.persistance.entity.UserDto;
import com.epam.training.ticketservice.service.helper.GenericConverter;
import org.springframework.shell.Availability;

public interface UserService extends GenericConverter<User, UserDto> {
    String signIn(String username, String password);

    String signOut();

    String describeAccount();

    Availability isCommandAvailable();
}
