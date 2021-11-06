package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Pair;

import java.util.List;

public interface ServiceHelper<T> {
    Pair<Boolean, String> isParamsValid(T t);

}
