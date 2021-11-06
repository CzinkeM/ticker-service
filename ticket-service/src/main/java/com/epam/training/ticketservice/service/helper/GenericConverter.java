package com.epam.training.ticketservice.service.helper;

import java.util.List;
import java.util.stream.Collectors;

public interface GenericConverter<T1, T2> {
    T2 convertModelToDto(T1 model);

    T1 convertDtoToModel(T2 dto);

    default List<T1> convertDtoListToModelList(List<T2> daoList) {
        return daoList.stream().map(this::convertDtoToModel).collect(Collectors.toList());
    }


}
