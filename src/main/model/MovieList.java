package model;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    private List<Movie> listOfMovie;
    private Movie newMovie;

    // EFFECTS: constructs an empty list of movie
    public MovieList() {
        listOfMovie = new ArrayList<>();
    }

    // REQUIRES: newMovie != null
    // MODIFIES: this
    // EFFECTS: adds a new movie into the list of movie
    public void addMovie() {
        listOfMovie.add(newMovie);
    }

    public Movie getNewMovie() {
        return newMovie;
    }

    public void setNewMovie(Movie m) {
        newMovie = m;
    }
}
