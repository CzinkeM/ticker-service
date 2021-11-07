package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.models.Movie;
import com.epam.training.ticketservice.models.Role;
import com.epam.training.ticketservice.models.User;
import com.epam.training.ticketservice.persistance.entity.MovieDto;
import com.epam.training.ticketservice.persistance.entity.UserDto;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.persistance.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Objects;
import java.util.Optional;

public class UserServiceImplTest {

    private UserServiceImpl underTest;
    private UserRepository userRepository;

    @BeforeEach
    public void init(){
        userRepository = Mockito.mock(UserRepository.class);
        underTest = new UserServiceImpl(userRepository);
    }
    @Test
    public void signInShouldReturnCorrespondingStringWhenUsernameIsNull() {
        String username = "admin";
        String password = "admin";
        var expected = "Username cannot be null during login";
        Mockito.when(userRepository.findByUsernameAndPassword("admin","admin")).thenReturn(Optional.empty());

        Assertions.assertThrows(NullPointerException.class,() -> underTest.signIn(null, password));
    }
    @Test
    public void signInShouldReturnCorrespondingStringWhenPasswordIsNull() {
        String username = "admin";
        String password = "admin";
        var expected = "Username cannot be null during login";
        Mockito.when(userRepository.findByUsernameAndPassword("admin",null)).thenReturn(Optional.empty());

        Assertions.assertThrows(NullPointerException.class,() -> underTest.signIn(null, password));
    }
    @Test
    public void signInShouldReturnCorrespondingStringWhenCredentialsValidButUserNotExist() {
        String username = "admin";
        String password = "admin";
        var expected = "Login failed due to incorrect credentials";
        Mockito.when(userRepository.findByUsernameAndPassword(username,password)).thenReturn(Optional.empty());

        var actual = underTest.signIn(username,password);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void signInShouldReturnCorrespondingStringWhenCredentialsValidAndUserNotExist() {
        String username = "admin";
        String password = "admin";
        var expected = "Signed in with privileged account 'admin'";
        Mockito.when(userRepository.findByUsernameAndPassword(username,password)).thenReturn(Optional.of(new UserDto(null, "admin", "admin", Role.ADMIN)));

        var actual = underTest.signIn(username,password);

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void signOutShouldReturnCorrespondingStringWhenUserNotLoggedIn() {
        var expected = "You are not signed in";

        var actual = underTest.signOut();

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void signOutShouldReturnCorrespondingStringWhenUserLoggedIn() {
        var expected = "You are sign out successfully!";

        underTest.loggedInUser = new User("admin","admin",Role.ADMIN);
        var actual = underTest.signOut();

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void describeAccountShouldReturnCorrespondingStringWhenUserLoggedIn() {
        var expected = "Signed in with privileged account 'admin'";

        underTest.loggedInUser = new User("admin","admin",Role.ADMIN);
        var actual = underTest.describeAccount();

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void describeAccountShouldReturnCorrespondingStringWhenUserNotLoggedIn() {
        var expected = "You are not signed in";

        underTest.loggedInUser = null;
        var actual = underTest.describeAccount();

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void convertDtoToModelShouldReturnMovieWhenUserDtoIsValid(){
        //Given
        UserDto given = new UserDto(null, "admin", "admin", Role.ADMIN);
        User expected = new User("admin", "admin", Role.ADMIN);
        //When
        User actual = underTest.convertDtoToModel(given);
        //Then
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void convertModelToDtoShouldReturnMovieDtoWhenMovieIsValid(){
        //Given
        UserDto expected = new UserDto(null, "admin", "admin", Role.ADMIN);
        User given = new User("admin", "admin", Role.ADMIN);
        //When
        UserDto actual = underTest.convertModelToDto(given);
        //Then
        Assertions.assertEquals(expected, actual);
    }

}
