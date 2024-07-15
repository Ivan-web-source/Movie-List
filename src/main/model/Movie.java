package model;

public class Movie {
    private String title;
    private String director;
    private String genre;
    private int duration;
    private int rating;

    // EFFECTS: construct a movie object with empty information
    public Movie() {

    }

    public void setTitle(String t) {
        this.title = t;
    }

    public String getTitle() {
        return title;
    }

    public void setDirector(String director) {
        this.director = director;
    } 

    public String getDirector() {
        return director;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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
}
