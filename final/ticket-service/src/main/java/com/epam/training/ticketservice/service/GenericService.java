package com.epam.training.ticketservice.service;

public interface GenericService<T> {
    String getAll();

    String delete(String identifier);

    String create(T t);

    String update(T t);
}
