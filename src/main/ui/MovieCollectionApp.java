// This class uses some formatting code that has been shown and used in 
// CPSC 210 Lab 3.

package ui;

import model.Movie;
import model.MovieCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.json.JSONWriter;

// Create an interactive MovieCollection app that can add new movies,
// see the list of unwatched movies, mark unwatched movies as watched movies,
// and give a personal rating for their watched movies.
public class MovieCollectionApp {
    private static final String JSON_STORE = "./data/myFile.json";
    private MovieCollection movieList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
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
    // EFFECTS: initialize the application objects with the starting values
    public void init() {
        input = new Scanner(System.in);
        movieList = new MovieCollection("Ivan's 1st file");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        isProgramRunning = true;
        movieCount = 0;
    }

    // EFFECTS: launch the menu display and user input
    public void handleMenu() {
        launchMenu();
        String choice = input.nextLine();
        processMenuCommands(choice);
    }

    // EFFECTS: prints a choice of input options 
    public void launchMenu() {
        System.out.println("Please enter the following option:\n");
        System.out.println("a: Add a new movie");
        System.out.println("v: View all list of movies");
        System.out.println("u: View unwatched movies");
        System.out.println("s -> save work room to file");
        System.out.println("l -> load work room from file");
        System.out.println("q: Exit application");
        lineDivider();
    }

    // EFFECTS: processes the user's input in the main menu
    public void processMenuCommands(String choice) {
        lineDivider();
        if (choice.equals("a")) {
            addMovie();
        } else if (choice.equals("v")) {
            viewListOfMovies();
        } else if (choice.equals("u")) {
            viewUnwatchedMovies();
        } else if (choice.equals("s")) {
            saveWorkRoom();
        } else if (choice.equals("l")) {
            loadWorkRoom();
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
        String title = input.nextLine();

        System.out.println("\nPlease enter the movie's director:");
        String director = input.nextLine();

        System.out.println("\nPlease enter the movie's genre:");
        String genre = input.nextLine();

        System.out.println("\nPlease enter the movie's duration (mins):");
        int duration = input.nextInt();
        input.nextLine(); 

        Movie newMovie = new Movie(title, director, genre, duration);

        movieList.addMovie(newMovie);
        System.out.println("\nNew Movie successfully added!");
    }

    // EFFECTS: shows a list of all added movies
    public void viewListOfMovies() {
        List<Movie> movieCollection = movieList.getMovieList();
        displayListOfMovies(movieCollection);
    }

    // MODIFIES: this
    // EFFECTS: shows a list of all the added movies by its title
    //          along with the movie order of being added into the list
    public void displayListOfMovies(List<Movie> movieList) {
        if (movieList.isEmpty()) {
            System.out.println("No movies added.");
            return;
        }

        System.out.println("Your Movie List:\n");
        for (Movie currentMovie : movieList) {
            movieCount++;
            System.out.println("Movie " + movieCount + " title: " + currentMovie.getTitle());
        }
        movieCount = 0;

        lineDivider();
        System.out.println("Enter e to expand your movie list.");
        System.out.println("Enter q to return to the main menu.");
        String viewButton = input.nextLine();
        while (!viewButton.equals("q") && !viewButton.equals("e")) {
            System.out.println("Incorrect input. Please try again.");
            System.out.println("Enter e to expand your movie list.");
            System.out.println("Enter q to return to the main menu.");
            viewButton = input.nextLine();
        }

        if (viewButton.equals("e")) {
            expandListOfMovies(movieList);
        }
    }

    // MODIFIES: this
    // EFFECTS: shows a list of all the added movies by its full received information
    //          along with the movie order of being added into the list
    private void expandListOfMovies(List<Movie> movieCollection) {
        System.out.println("Your Movie List:\n");
        for (Movie currentMovie : movieCollection) {
            movieCount++;
            System.out.println("Movie " + movieCount + " title: " + currentMovie.getTitle());
            System.out.println("Director: " + currentMovie.getDirector());
            System.out.println("Genre: " + currentMovie.getGenre());
            System.out.println("Duration (mins): " + currentMovie.getDuration());
            if (currentMovie.getWatchedStatus() == true) {
                System.out.println("Rating (personal): " + currentMovie.getRating());
            }
            lineDivider();
        }
        lineDivider();
        System.out.println("Enter q to return to the main menu.");
        String expandButton = input.nextLine();
        while (!expandButton.equals("q")) {
            System.out.println("Incorrect input. Please try again.");
            System.out.println("Enter q to return to the main menu.");
            expandButton = input.nextLine();
        }
    }

    // MODIFIES: unwatchedMovies
    // EFFECTS: shows a list of all unwatched movies 
    public void viewUnwatchedMovies() {

        movieList.viewUnwatched();

        displayUnwatchedMovies(movieList.getUnwatchedList());
    }

    // MODIFIES: this
    // EFFECTS: shows a list of all the unwatched movies by its title
    //          and its order of being added into the list
    public void displayUnwatchedMovies(List<Movie> unwatchedMovies) {
        if (unwatchedMovies.isEmpty()) {
            System.out.println("No movies added.");
            return;
        }

        System.out.println("Your Movie List:\n");
        for (Movie currentMovie : unwatchedMovies) {
            movieCount++;
            System.out.println("Movie " + movieCount + ": " + currentMovie.getTitle());
        }
        editOption(unwatchedMovies);
        movieCount = 0;
        lineDivider();
    }

    // MODIFIES: this
    // EFFECTS: launch a mark and rate option in the list of unwatched movies
    public void editOption(List<Movie> unwatchedMovies) {
        System.out.println("Enter 'm' to mark a movie as watched.");
        lineDivider();
        String markChoice = input.nextLine();

        if (markChoice.equals("m")) {
            selectForRating(unwatchedMovies);
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    // REQUIRES: numberInput <= unwatchedMovies.length()
    // MODIFIES: this
    // EFFECTS: select a movie in the list to be rated by the user
    public void selectForRating(List<Movie> unwatchedMovies) {
        System.out.println("Enter a number for the movie you want to rate");
        int numberInput = input.nextInt();
        input.nextLine();
        int movieIndex = numberInput - 1;
        Movie movieToRate = unwatchedMovies.get(movieIndex);
        enterRating(movieToRate);
    }
        
    // REQUIRES: Movie != null 
    // MODIFIES: this
    // EFFECTS: receives a rating (1-5) and save it for a movie
    public void enterRating(Movie movie) {
        System.out.println("Please enter a rating for the movie.");
        int rateInput = input.nextInt();
        input.nextLine();
        while (rateInput < 1 || rateInput > 5) {
            System.out.println("Enter a number between 1-5!");
            rateInput = input.nextInt();
            input.nextLine();
        }
        movie.setRating(rateInput);
        movie.markAsWatched();
        System.out.println("A rating of " + rateInput + " has been received.");
    }

    // EFFECTS: saves the movie collection to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(movieList);
            jsonWriter.close();
            System.out.println("Saved " + movieList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads movie collection from file
    private void loadWorkRoom() {
        try {
            movieList = jsonReader.read();
            System.out.println("Loaded " + movieList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
    
    // MODIFIES: this
    // EFFECTS: prints a closing message and stops the application
    public void exitApplication() {
        System.out.println("Have a good day!");
        isProgramRunning = false;
    }

    // EFFECTS: prints a line dashed to separate the app introduction
    private void lineDivider() {
        System.out.println("-------------------------------");
    }
}
