package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Creates a list of movie object that contains the detail of each movie information
public class MovieCollection implements Writable {
    private String name;
    private List<Movie> movieList;
    private List<Movie> unwatchedList;

    // EFFECTS: creates two empty movie list
    public MovieCollection(String name) {
        this.name = name;
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
        unwatchedList.clear();
        for (Movie currentMovie : movieList) {
            if (currentMovie.getWatchedStatus() == false) {
                unwatchedList.add(currentMovie);
            }
        }
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("movies", moviesToJson());
        return json;
    }

    // EFFECTS: returns things in this movie collection as a JSON array
    private JSONArray moviesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie t : movieList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
