package model;

import java.util.ArrayList;
import java.util.List;

public class MovieCollection {
    private List<Movie> movieList;
    private List<Movie> unwatchedList;

    public MovieCollection() {
        movieList = new ArrayList<>();
        unwatchedList = new ArrayList<>();
    }

    public void addMovie(Movie newMovie) {
        movieList.add(newMovie);
    }

    public void addUnwatchedMovie(Movie unwatchedMovie) {
        movieList.add(unwatchedMovie);
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public List<Movie> getUnwatchedList() {
        return unwatchedList;
    }

    public void viewUnwatched() {
        for (Movie currentMovie : movieList) {
            if (currentMovie.getWatchedStatus() == false) {
                unwatchedList.add(currentMovie);
            }
        }
    }
}
