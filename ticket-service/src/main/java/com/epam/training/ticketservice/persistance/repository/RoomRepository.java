package com.epam.training.ticketservice.persistance.repository;

import com.epam.training.ticketservice.persistance.entity.RoomDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomDto, Integer> {
}
