package com.epam.training.ticketservice.models;

import com.epam.training.ticketservice.persistance.entity.MovieDto;
import com.epam.training.ticketservice.service.helper.DateConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Screening {

    private Movie movie;
    private String roomName;
    private String startDateString;
    private Date endDate;
    private Date startDate;

    public Screening(Movie movie, String roomName, String startDate) {
        this.movie = movie;
        this.roomName = roomName;
        this.startDateString = startDate;
        this.startDate = DateConverter.convertStringToDate(startDate);
        this.endDate = calculateEndOfScreening();
    }

    private Date calculateEndOfScreening() {
        var movieLengthInMilliseconds = movie.getLength() * 60000L;
        return new Date(startDate.getTime() + movieLengthInMilliseconds);
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getMovieName() {
        return movie.getTitle();
    }

    public String getStartDateString() {
        return startDateString;
    }

    public Movie getMovie() {
        return movie;
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
        return Objects.equals(movie, screening.movie)
                && Objects.equals(roomName, screening.roomName)
                && Objects.equals(startDateString, screening.startDateString)
                && Objects.equals(endDate, screening.endDate)
                && Objects.equals(startDate, screening.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, roomName, startDateString, endDate, startDate);
    }

    @Override
    public String toString() {
        return movie.getTitle()
                + " (" + movie.getGenre()
                + ", " + movie.getLength()
                + " minutes)"
                + ", screened in room "
                + roomName
                + ", at "
                + DateConverter.convertDateToString(startDate) + "\n";
    }
}
