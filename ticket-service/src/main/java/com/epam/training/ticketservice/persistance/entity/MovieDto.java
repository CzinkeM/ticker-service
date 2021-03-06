package com.epam.training.ticketservice.persistance.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.util.Objects;

@Entity
public class MovieDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String title;
    private String genre;
    private Integer length;

    public MovieDto() {
    }

    public MovieDto(Integer id, String title, String genre, Integer length) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.length = length;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieDto movie = (MovieDto) o;
        return Objects.equals(id, movie.id)
                && Objects.equals(title, movie.title)
                && Objects.equals(genre, movie.genre)
                && Objects.equals(length, movie.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, length);
    }

    @Override
    public String toString() {
        return "Movie{"
                + "id="
                + id
                + ", title='"
                + title
                + '\''
                + ", genre='"
                + genre
                + '\''
                + ", length="
                + length
                + '}';
    }
}
