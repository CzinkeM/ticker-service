package com.epam.training.ticketservice.models;

import java.util.Objects;

public class Room {

    private final int capacity;
    private String name;
    private int rows;
    private int columns;

    public Room(String name, int rows, int columns) {
        this.name = name;
        this.rows = rows;
        this.columns = columns;
        this.capacity = rows * columns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Room room = (Room) o;
        return rows == room.rows
                && columns == room.columns
                && capacity == room.capacity
                && Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rows, columns, capacity);
    }

    @Override
    public String toString() {
        return "Room " + name + " with " + capacity + " seats, " + rows + " rows and " + columns + " columns\n";
    }
}
