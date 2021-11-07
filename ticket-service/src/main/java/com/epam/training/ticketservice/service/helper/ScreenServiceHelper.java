package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Pair;
import com.epam.training.ticketservice.models.Room;
import com.epam.training.ticketservice.models.Screening;
import com.epam.training.ticketservice.persistance.entity.ScreeningDto;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.persistance.repository.RoomRepository;
import com.epam.training.ticketservice.persistance.repository.ScreeningRepository;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.RoomService;
import org.springframework.security.core.parameters.P;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class ScreenServiceHelper implements GenericConverter<Screening, ScreeningDto> {


    //TODO: we can implement converters here as well
    private static final long BREAK_TIME_IN_MILLISECOND = 600000L;
    private final ScreeningRepository screeningRepository;
    private final RoomService roomService;
    private final MovieService movieService;

    public ScreenServiceHelper(
            ScreeningRepository screeningRepository,
            RoomService roomService,
            MovieService movieService) {
        this.screeningRepository = screeningRepository;
        this.roomService = roomService;
        this.movieService = movieService;
    }

    public Pair<Boolean, String> isValid(ScreeningDto screeningDto) {
        if (!isMovieValid(screeningDto.getMovieTitle())) {
            new Pair<>(false, "Movie does not exist");
        }

        if (!isRoomValid(screeningDto.getRoomName())) {
            new Pair<>(false, "Room does not exist");
        }

        var validator = isScreeningIsValid(screeningDto);
        if (!validator.getFirst()) {
            return validator;
        }

        return new Pair<>(true, "It is valid");
    }


    private List<Screening> getAllScreenings() {
        return screeningRepository
                .findAll()
                .stream()
                .map(this::convertDtoToModel)
                .collect(Collectors.toList());
    }

    private Pair<Boolean, String> isScreeningIsValid(ScreeningDto dto) {
        var screeningToValidate = convertDtoToModel(dto);
        var allScreening = getAllScreenings();
        var reservedRoomsList = allScreening
                .stream()
                .map(Screening::getRoomName)
                .collect(Collectors.toList());
        if (allScreening.isEmpty()) {
            return new Pair<>(true, "It ok");
        }
        if (!reservedRoomsList.contains(screeningToValidate.getRoomName())) {
            return new Pair<>(true, "It ok");
        }
        for (Screening screening: allScreening) {
            if (isScreeningStartAtBreakTime(screeningToValidate, screening)) {
                return new Pair<>(false,"This would start in the break period after another screening in this room");
            }

            if (isScreenOverlapWithOther(screeningToValidate, screening)) {
                return new Pair<>(false,"There is an overlapping screening");
            }
        }
        return new Pair<>(true, "It ok");
    }

    private boolean isScreeningStartAtBreakTime(Screening toValidate, Screening toCompareWith) {
        var startOfScreeningToValidate = toValidate.getStartDate();
        var endOfScreeningToValidate = toValidate.getEndDate();
        var startOfScreeningToCompare = toCompareWith.getStartDate();
        var endOfScreeningToCompare = toCompareWith.getEndDate();
        //comes after a screening
        return startOfScreeningToValidate.after(endOfScreeningToCompare)
                && startOfScreeningToValidate.before(
                        new Date(endOfScreeningToCompare.getTime() + BREAK_TIME_IN_MILLISECOND));

    }

    private boolean isScreenOverlapWithOther(Screening toValidate, Screening toCompareWith) {
        return !(toValidate.getEndDate().before(toCompareWith.getStartDate())
                || toValidate.getStartDate().after(toCompareWith.getEndDate()));
    }

    private boolean isMovieValid(String title) {
        return movieService.isMovieExist(title);
    }

    private boolean isRoomValid(String name) {
        return roomService.isRoomExist(name);
    }

    @Override
    public ScreeningDto convertModelToDto(Screening model) {
        return new ScreeningDto(null, model.getMovieName(), model.getRoomName(), model.getStartDateString());
    }

    @Override
    public Screening convertDtoToModel(ScreeningDto dto) {
        var movie = movieService.getMovieByName(dto.getMovieTitle());
        return new Screening(movie,dto.getRoomName(),dto.getDateOfScreening());
    }
}
