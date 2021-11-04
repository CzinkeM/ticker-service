package com.epam.training.ticketservice.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.util.Objects;

@Entity
public class RoomDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String name;

    private Integer rows;
    private int column;

    public RoomDto() {
    }

    public RoomDto(Integer id, String name, Integer rows, int column) {
        this.id = id;
        this.name = name;
        this.rows = rows;
        this.column = column;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RoomDto roomDto = (RoomDto) o;
        return column == roomDto.column && Objects.equals(id, roomDto.id) && Objects.equals(name, roomDto.name)
                && Objects.equals(rows, roomDto.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rows, column);
    }

    @Override
    public String toString() {
        return "RoomDto{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", rows="
                + rows
                + ", column="
                + column
                + '}';
    }
}
