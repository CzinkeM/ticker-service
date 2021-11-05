package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.models.Pair;
import com.epam.training.ticketservice.models.Screening;
import com.epam.training.ticketservice.persistance.entity.ScreeningDto;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.persistance.repository.RoomRepository;
import com.epam.training.ticketservice.persistance.repository.ScreeningRepository;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.service.helper.ScreenServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreenServiceHelper helper = new ScreenServiceHelper();
    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public ScreeningServiceImpl(
            ScreeningRepository screeningRepository,
            MovieRepository movieRepository,
            RoomRepository roomRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public String getAll() {
        List<Screening> listOfScreening = screeningRepository
                .findAll().stream()
                .map(this::convertDtoToModel)
                .collect(Collectors.toList());
        if (listOfScreening.isEmpty()) {
            return "There are no screenings";
        }
        return helper.prettyListString(listOfScreening);
    }

    @Override
    public String delete(String identifier) {
        Optional<ScreeningDto> screeningToDelete = screeningRepository
                .findByDateOfScreening(identifier);
        if (screeningToDelete.isPresent()) {
            screeningRepository.delete(screeningToDelete.get());
            return screeningToDelete.get().getDateOfScreening() + " DELETED!";
        } else {
            return "The screening with identifier: " + identifier + " does not exist.";
        }
    }

    @Override
    public String create(Screening screening) {
        Pair<Boolean, String> validator = helper.isValid(screening);
        if (!validator.getFirst()) {
            return validator.getSecond();
        } else {
            if (!helper.isMovieValid(movieRepository,screening.getMovieTitle())) {
                return  "Movie does not exist";
            }
            screening.setMovie(movieRepository.findByTitle(screening.getMovieTitle()).get());

            if (!helper.isRoomValid(roomRepository,screening.getRoomName())) {
                return "Room does not exist";
            }
            try {
                if (helper.isDateValid(screeningRepository.findAll().stream()
                        .map(this::convertDtoToModel)
                        .collect(Collectors.toList()),screening)) {
                    screeningRepository.save(convertModelToDto(screening));
                    return screening.getStartOfScreeningString() + " CREATED";
                }
            } catch (ParseException e) {
                //Should throw some errors message
                e.printStackTrace();
            }
            return "";
        }
    }

    @Override
    public String update(Screening screening) {
        return "You can not update screenings";
    }

    @Override
    public Screening convertDtoToModel(ScreeningDto dto) {
        var screening = new Screening(dto.getMovieTitle(), dto.getRoomName(),dto.getDateOfScreening());
        if (helper.isMovieValid(movieRepository,dto.getMovieTitle())) {
            screening.setMovie(movieRepository.findByTitle(dto.getMovieTitle()).get());
        }
        return screening;
    }

    @Override
    public ScreeningDto convertModelToDto(Screening model) {
        return new ScreeningDto(model.getMovieTitle(), model.getRoomName(), model.getStartOfScreeningString());
    }
}
