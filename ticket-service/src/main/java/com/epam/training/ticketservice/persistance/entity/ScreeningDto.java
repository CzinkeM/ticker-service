package com.epam.training.ticketservice.persistance.entity;

import com.epam.training.ticketservice.models.Movie;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.util.Objects;

@Entity
public class ScreeningDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String movieTitle;
    private String roomName;
    @Column(unique = true)
    private String dateOfScreening;

    public ScreeningDto() {
    }

    public ScreeningDto(Integer id,String movieTitle, String roomName, String dateOfScreening) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.roomName = roomName;
        this.dateOfScreening = dateOfScreening;
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

    public String getDateOfScreening() {
        return dateOfScreening;
    }

    public void setDateOfScreening(String dateOfScreening) {
        this.dateOfScreening = dateOfScreening;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ScreeningDto that = (ScreeningDto) o;
        return Objects.equals(movieTitle, that.movieTitle)
                && Objects.equals(roomName, that.roomName)
                && Objects.equals(dateOfScreening, that.dateOfScreening);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieTitle, roomName, dateOfScreening);
    }
}
