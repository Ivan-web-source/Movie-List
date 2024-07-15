package model;

public class Movie {
    private String title;
    private String director;
    private String genre;
    private int duration;
    private int rating;

    // EFFECTS: construct a movie object
    public Movie() {

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
}
