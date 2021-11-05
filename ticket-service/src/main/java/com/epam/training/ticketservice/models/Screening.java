package com.epam.training.ticketservice.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Screening {

    private String movieTitle;
    private String roomName;
    private String startOfScreeningString;
    private int screeningTime;

    public Screening(String movieTitle, String roomName, String startOfScreeningString) {
        this.movieTitle = movieTitle;
        this.roomName = roomName;
        this.startOfScreeningString = startOfScreeningString;
    }

    public String getStartOfScreeningString() {
        return startOfScreeningString;
    }

    public void setStartOfScreeningString(String startOfScreeningString) {
        this.startOfScreeningString = startOfScreeningString;
    }

    public int getScreeningTime() {
        return screeningTime;
    }

    public void setScreeningTime(int screeningTime) {
        this.screeningTime = screeningTime;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
