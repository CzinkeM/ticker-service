package com.epam.training.ticketservice.service.helper;

public interface GenericConverter<Model, DTO> {
    Model convertDtoToModel(DTO dto);

    DTO convertModelToDto(Model model);
}
