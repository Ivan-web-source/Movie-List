package ui;

import model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieCollectionApp {
    private List<Movie> listOfMovie;
    private Movie newMovie;
    private Scanner input;

    // EFFECTS: constructs an empty list of movie
    public MovieCollectionApp() {
        listOfMovie = new ArrayList<>();
        newMovie = new Movie();
    }

    // REQUIRES: newMovie != null
    // MODIFIES: this
    // EFFECTS: adds a new movie into the list of movie and resets movie to null
    public void addMovie() {
        listOfMovie.add(newMovie);
        newMovie = null;
    }

    public Movie getNewMovie() {
        return newMovie;
    }

    public void setNewMovie(Movie m) {
        newMovie = m;
    }

    // REQUIRES: newMovie != null
    // MODIFIES: this
    // EFFECTS: receives a rating (1-5) and save it for a movie
    public void enterRating() {
        System.out.println("Please enter a rating for the movie.");
        int rateInput = input.nextInt();
        while (rateInput < 1 || rateInput >5) {
            System.out.println("Enter a number between 1-5!");
            rateInput = input.nextInt();
        }
        newMovie.setRating(rateInput);
        System.out.println("Rating " + rateInput + "has been received.");
    }
}

