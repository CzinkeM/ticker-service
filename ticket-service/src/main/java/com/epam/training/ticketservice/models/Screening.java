package com.epam.training.ticketservice.models;

import com.epam.training.ticketservice.persistance.entity.MovieDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Screening {

    private String movieTitle;
    private String roomName;
    private String startOfScreeningString;
    private int screeningTime;
    private MovieDto movie;

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

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Screening screening = (Screening) o;
        return screeningTime == screening.screeningTime
                && Objects.equals(movieTitle, screening.movieTitle)
                && Objects.equals(roomName, screening.roomName)
                && Objects.equals(startOfScreeningString, screening.startOfScreeningString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieTitle, roomName, startOfScreeningString, screeningTime);
    }

    @Override
    public String toString() {
        return movieTitle + "(" + movie.getGenre() + " , " + movie.getLength()
                + " minutes)" + ", screened in room " + roomName + ", at " + startOfScreeningString + "\n";
    }
}
