package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Pair;
import com.epam.training.ticketservice.models.Screening;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.persistance.repository.RoomRepository;
import com.epam.training.ticketservice.persistance.repository.ScreeningRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class ScreenServiceHelper implements ServiceHelper<Screening> {

    private static final int breakTimeInMinutes = 10;

    public boolean isMovieValid(MovieRepository repository, String title) {
        var searchedMovie = repository.findByTitle(title);
        return searchedMovie.isPresent();
    }

    public boolean isRoomValid(RoomRepository repository, String name) {
        var searchedRoom = repository.findByName(name);
        return searchedRoom.isPresent();
    }

    public boolean isDateValid(List<Screening> screenings, Screening screeningToValidate) throws ParseException {
        var dateToValidateStartInMinutes = convertStringToDate(screeningToValidate
                .getStartOfScreeningString())
                .getMinutes();
        var dateToValidateFinishInMinutes = dateToValidateStartInMinutes + screeningToValidate.getScreeningTime();
        var validatorArray = new ArrayList<Boolean>();
        screenings.forEach(screening -> {
            try {
                var screeningStart = convertStringToDate(screening.getStartOfScreeningString()).getMinutes();
                var screeningEnd = screeningStart + screening.getScreeningTime();
                if (dateToValidateStartInMinutes > screeningEnd + breakTimeInMinutes
                        || dateToValidateFinishInMinutes + breakTimeInMinutes < screeningStart) {
                    validatorArray.add(true);
                } else {
                    validatorArray.add(false);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return !validatorArray.contains(false);
    }

    private Date convertStringToDate(String dateAsString) throws ParseException {
        var format = "YYYY-MM-DD hh:mm";
        return new SimpleDateFormat(format).parse(dateAsString);
    }

    @Override
    public Pair<Boolean, String> isValid(Screening screening) {
        Objects.requireNonNull(screening, "Screening cannot be null");
        Objects.requireNonNull(screening.getMovieTitle(), "Screening movie title cannot be null");
        Objects.requireNonNull(screening.getRoomName(), "Screening room name cannot be null");
        Objects.requireNonNull(screening.getStartOfScreeningString(), "Screening date cannot be null");
        return new Pair<>(true, "Its valid");
    }

}
