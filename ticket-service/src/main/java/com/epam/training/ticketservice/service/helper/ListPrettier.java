package com.epam.training.ticketservice.service.helper;

import java.util.List;
import java.util.Objects;

public interface ListPrettier<T> {
    default String prettyListString(List<T> ts) {
        StringBuilder prettyList = new StringBuilder();
        for (T t : ts) {
            prettyList.append(t.toString());
        }
        return prettyList.toString();
    }
}
