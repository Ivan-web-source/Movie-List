package ui;

import model.Movie;

import java.util.*;

public class MovieCollectionApp {
    private List<Movie> listOfMovies;
    private Scanner input;
    private boolean isProgramRunning;
    private int movieCount;

    // EFFECTS: constructs an instance of Movie Collection console ui application
    public MovieCollectionApp() {
        init();

        lineDivider();
        System.out.println("Welcome to my MovieCollection!");
        lineDivider();

        while (isProgramRunning) {
            handleMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: initialize the application objects with starting values
    public void init() {
        input = new Scanner(System.in);
        listOfMovies = new ArrayList<>();
        isProgramRunning = true;
        movieCount = 0;
    }

    // EFFECTS: launch the menu display and process the user input
    public void handleMenu() {
        launchMenu();
        String choice = input.nextLine();
        processMenuCommands(choice);
    }

    // EFFECTS: launch a choice of input options 
    public void launchMenu() {
        System.out.println("Please enter the following option:\n");
        System.out.println("a: Add a new movie");
        System.out.println("u: View unwatched movies");
        System.out.println("q: Exit application");
        lineDivider();
    }

    // EFFECTS: processes the user's input in the main menu
    public void processMenuCommands(String choice) {
        lineDivider();
        if (choice.equals("a")) {
            addMovie();
        } else if (choice.equals("u")) {
            viewUnwatchedMovies();
        } else if (choice.equals("q")) {
            exitApplication();
        } else {
            System.out.println("An unavailable input was given. Please try again.");
        }
        lineDivider();
    }

    // MODIFIES: this
    // EFFECTS: adds a new movie into the list of movie
    public void addMovie() {
        System.out.println("Please enter the movie's title:");
        String title = this.input.nextLine();

        System.out.println("\nPlease enter the movie's director:");
        String director = this.input.nextLine();

        System.out.println("\nPlease enter the movie's genre:");
        String genre = this.input.nextLine();

        System.out.println("\nPlease enter the movie's duration:");
        int duration = this.input.nextInt();

        Movie newMovie = new Movie(title, director, genre, duration);

        listOfMovies.add(newMovie);
        System.out.println("\nNew Movie successfully added!");
    }

    // MODIFIES: this
    // EFFECTS: shows a list of unwatched movies
    public void viewUnwatchedMovies() {
        List<Movie> unwatchedMovies = new ArrayList<>();

        for (Movie currentMovie : listOfMovies) {
            if (currentMovie.getWatchedStatus() == false) {
                unwatchedMovies.add(currentMovie);
            }
        }

        displayGivenMovies(unwatchedMovies);
    }

    // MODIFIES: this
    // EFFECTS: shows a list of all the added movies by its title
    public void displayGivenMovies(List<Movie> listOfMovies) {
        if (listOfMovies.isEmpty()) {
            System.out.println("No movies added.");
            return;
        }

        System.out.println("Your Movie List:\n");
        for (Movie currentMovie : listOfMovies) {
            movieCount++;
            System.out.println("Movie " + movieCount + ": " + currentMovie.getTitle());
            editOption(currentMovie);
        }
        movieCount = 0;
        lineDivider();
    }

    // MODIFIES: this
    // EFFECTS: launch a mark and rate option in the list of watched movies
    public void editOption(Movie currentMovie) {
        System.out.println("Enter 'm' to mark this movie as watched.");
        lineDivider();
        String markChoice = input.nextLine();

        if (markChoice.equals("m")) {
            currentMovie.markAsWatched();
            enterRating(currentMovie);
        } else {
            System.out.println("Invalid option. Please try again.");
        }
        lineDivider();
    }

    // REQUIRES: newMovie != null
    // MODIFIES: this
    // EFFECTS: receives a rating (1-5) and save it for a movie
    public void enterRating(Movie Movie) {
        System.out.println("Please enter a rating for the movie.");
        int rateInput = input.nextInt();
        while (rateInput < 1 || rateInput >5) {
            System.out.println("Enter a number between 1-5!");
            rateInput = input.nextInt();
        }
        Movie.setRating(rateInput);
        System.out.println("A rating of " + rateInput + "has been received.");
    }
    
    // MODIFIES: this
    // EFFECTS: prints a closing message and stops the application
    public void exitApplication() {
        System.out.println("Have a good day!");
        isProgramRunning = false;
    }

    // EFFECTS: prints a line dashed to separate the app introduction
    private void lineDivider() {
        System.out.println("------------------------------");
    }
}