package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.models.Pair;
import com.epam.training.ticketservice.models.Screening;
import com.epam.training.ticketservice.persistance.entity.ScreeningDto;
import com.epam.training.ticketservice.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.persistance.repository.RoomRepository;
import com.epam.training.ticketservice.persistance.repository.ScreeningRepository;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.service.helper.ListPrettier;
import com.epam.training.ticketservice.service.helper.ScreenServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScreeningServiceImpl implements ScreeningService, ListPrettier<Screening> {

    private final ScreenServiceHelper helper;
    private final ScreeningRepository screeningRepository;
    private final MovieService movieService;
    private final RoomService roomService;

    @Autowired
    public ScreeningServiceImpl(
            ScreeningRepository screeningRepository,
            MovieService movieService,
            RoomService roomService) {
        this.screeningRepository = screeningRepository;
        this.movieService = movieService;
        this.roomService = roomService;
        helper = new ScreenServiceHelper(screeningRepository,roomService,movieService);
    }

    @Override
    public String getAll() {
        List<Screening> listOfScreening = helper.convertDtoListToModelList(screeningRepository.findAll());
        if (listOfScreening.isEmpty()) {
            return "There are no screenings";
        }
        return prettyListString(listOfScreening);
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
    public String create(ScreeningDto screeningDto) {
        var validator = helper.isValid(screeningDto);
        if (validator.getFirst()) {
            screeningRepository.save(screeningDto);
            return "Created Screening";
        }

        return validator.getSecond();
    }

    @Override
    public String update(ScreeningDto screeningDto) {
        return "You can not update screenings";
    }
}
