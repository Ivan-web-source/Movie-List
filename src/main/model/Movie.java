package model;

import org.json.JSONObject;

import persistence.Writable;

// Creates a movie object that has some relevant details like title, director,
// genre, duration. The movie object also has an identifier for user to control
// the watched status and personal rating.
public class Movie implements Writable {
    private String title;
    private String director;
    private String genre;
    private int duration;
    private int rating;
    private boolean isWatched;

    // EFFECTS: construct a movie object with the given information in the parameter
    //          and with initial rating because movie hasn't been watched
    public Movie(String title,String director,String genre,int duration) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.duration = duration;
        rating = 0;
        isWatched = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setRating(int r) {
        rating = r;
    }

    public int getRating() {
        return rating;
    }

    // MODIFIES: this
    // EFFECTS: mark a movie status as watched
    public void markAsWatched() {
        isWatched = true;
    }

    public boolean getWatchedStatus() {
        return isWatched;
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("director", director);
        json.put("genre", genre);
        json.put("duration", duration);
        json.put("watch status", isWatched);
        json.put("rating", rating);
        return json;
    }
}
