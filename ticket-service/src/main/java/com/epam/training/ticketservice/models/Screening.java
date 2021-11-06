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
    public String toString() {
        return movie.getTitle() + " (" + movie.getGenre() + ", " + movie.getLength()
                + " minutes)" + ", screened in room " + roomName + ", at " + startDate + "\n";
    }
}
