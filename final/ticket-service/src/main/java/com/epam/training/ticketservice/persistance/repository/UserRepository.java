package com.epam.training.ticketservice.persistance.repository;

import com.epam.training.ticketservice.persistance.entity.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Integer> {
    Optional<UserDto> findByUsernameAndPassword(String username, String password);
}
