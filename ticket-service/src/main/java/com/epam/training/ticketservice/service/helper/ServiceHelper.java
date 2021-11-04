package com.epam.training.ticketservice.service.helper;

import com.epam.training.ticketservice.models.Pair;

import java.util.List;

public interface ServiceHelper<T> {
    Pair<Boolean, String> isValid(T t);

    default String prettyListString(List<T> movies) {
        StringBuilder prettyList = new StringBuilder();
        for (T t : movies) {
            prettyList.append(t.toString());
        }
        return prettyList.toString();
    }
}
