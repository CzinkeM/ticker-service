package com.epam.training.ticketservice.persistance.repository;

import com.epam.training.ticketservice.persistance.entity.MovieDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieDto, Integer> {
    Optional<MovieDto> findByTitle(String identifier);
}
