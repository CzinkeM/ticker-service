package com.epam.training.ticketservice.cli.command;

import com.epam.training.ticketservice.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class UserCommand {

    private final UserServiceImpl userService;

    @Autowired
    public UserCommand(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ShellMethod(value = "Sign in with username and password.", key = "sign in")
    public String signIn(String user, String password) {
        return userService.signIn(user, password);
    }

    @ShellMethod(value = "Sign out from current account.", key = "sign out")
    public String signOut() {
        return userService.signOut();
    }

    @ShellMethod(value = "Describe account, retrieve username and role.", key = "describe account")
    public String describeAccount() {
        return userService.describeAccount();
    }
}
