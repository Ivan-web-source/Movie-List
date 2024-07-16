package model;

public class Movie {
    private String title;
    private String director;
    private String genre;
    private int duration;
    private int rating;
    private boolean isWatched;

    // EFFECTS: construct a movie object with empty information
    public Movie(String title,String director,String genre,int duration) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.duration = duration;
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

    public void markAsWatched() {
        isWatched = true;
    }

    public boolean getWatchedStatus() {
        return isWatched;
    }
}
