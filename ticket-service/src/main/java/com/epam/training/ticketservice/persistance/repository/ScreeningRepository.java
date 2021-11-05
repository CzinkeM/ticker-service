package com.epam.training.ticketservice.persistance.repository;

import com.epam.training.ticketservice.persistance.entity.RoomDto;
import com.epam.training.ticketservice.persistance.entity.ScreeningDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScreeningRepository extends JpaRepository<ScreeningDto, Integer> {
    Optional<ScreeningDto> findByDateOfScreening(String date);
}
