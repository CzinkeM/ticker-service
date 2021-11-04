package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.models.Role;
import com.epam.training.ticketservice.models.User;
import com.epam.training.ticketservice.persistance.entity.UserDto;
import com.epam.training.ticketservice.persistance.repository.UserRepository;
import com.epam.training.ticketservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public User loggedInUser = null;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        UserDto admin = new UserDto(null, "admin", "admin", Role.ADMIN);
        userRepository.save(admin);
    }


    @Override
    public String signIn(String username, String password) {
        Objects.requireNonNull(username, "Username cannot be null during login");
        Objects.requireNonNull(password, "Password cannot be null during login");
        UserDto retrievedUserFormDatabase = userRepository.findByUsernameAndPassword(username, password).orElse(null);
        if (retrievedUserFormDatabase == null) {
            return "Login failed due to incorrect credentials";
        }
        loggedInUser = convertDtoToModel(retrievedUserFormDatabase);
        return "Welcome " + loggedInUser.getUsername() + " !";
    }

    @Override
    public String signOut() {
        if (loggedInUser != null) {
            loggedInUser = null;
            return "You are sign out successfully!";
        }
        return "You are not signed in.";
    }

    @Override
    public String describeAccount() {
        if (loggedInUser.getRole() == Role.ADMIN) {
            return "Signed in with privileged account " + loggedInUser.getUsername();
        }
        return "You are not signed in.";
    }

    @Override
    public Availability isCommandAvailable() {
        User user;
        if (loggedInUser != null) {
            user = loggedInUser;
        } else {
            return Availability.unavailable("You are not signed in");
        }
        if (user.getRole() == Role.ADMIN) {
            return Availability.available();
        } else {
            return Availability.unavailable("You are not signed in");
        }
    }

    @Override
    public User convertDtoToModel(UserDto userDto) {
        return new User(userDto.getUsername(), userDto.getPassword(), userDto.getRole());
    }

    @Override
    public UserDto convertModelToDto(User user) {
        return null;
    }
}
