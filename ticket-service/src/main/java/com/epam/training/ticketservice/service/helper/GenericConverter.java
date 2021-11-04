package com.epam.training.ticketservice.service.helper;

public interface GenericConverter<T, U> {
    T convertDtoToModel(U dto);

    U convertModelToDto(T model);
}
