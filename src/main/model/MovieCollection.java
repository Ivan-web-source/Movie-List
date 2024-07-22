package model;

import java.util.ArrayList;
import java.util.List;

public class MovieCollection {
    private List<Movie> movieList;
    private List<Movie> unwatchedList;

    // EFFECTS: creates two empty movie list
    public MovieCollection() {
        movieList = new ArrayList<>();
        unwatchedList = new ArrayList<>();
    }

    // REQUIRES: !movieList.contains(newMovie)
    // MODIFIES: this
    // EFFECTS: adds a movie into the list of movie
    public void addMovie(Movie newMovie) {
        movieList.add(newMovie);
    }

    // EFFECTS: returns a list of movie (watched and unwatched)
    public List<Movie> getMovieList() {
        return movieList;
    }

    // EFFECTS: returns a list of unwatched movie
    public List<Movie> getUnwatchedList() {
        return unwatchedList;
    }

    // MODIFIES: this
    // EFFECTS: adds a movie into the list of unwatched movie
    public void viewUnwatched() {
        for (Movie currentMovie : movieList) {
            if (currentMovie.getWatchedStatus() == false) {
                unwatchedList.add(currentMovie);
            }
        }
    }
}
