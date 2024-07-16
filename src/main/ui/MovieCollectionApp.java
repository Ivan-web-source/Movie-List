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
    // EFFECTS: initialize the application objects with the starting values
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

    // EFFECTS: prints a choice of input options 
    public void launchMenu() {
        System.out.println("Please enter the following option:\n");
        System.out.println("a: Add a new movie");
        System.out.println("v: View all list of movies");
        System.out.println("u: View unwatched movies");
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

        listOfMovies.add(newMovie);
        System.out.println("\nNew Movie successfully added!");
    }

    // MODIFIES: this
    // EFFECTS: shows a list of all added movies
    public void viewListOfMovies() {
        displayListOfMovies(listOfMovies);
    }

    // MODIFIES: this
    // EFFECTS: shows a list of all the added movies by its title
    public void displayListOfMovies(List<Movie> listOfMovies) {
        if (listOfMovies.isEmpty()) {
            System.out.println("No movies added.");
            return;
        }

        System.out.println("Your Movie List:\n");
        for (Movie currentMovie : listOfMovies) {
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
            expandListOfMovies();
        }
    }

    // MODIFIES: this
    // EFFECTS: shows a list of all the added movies by its full received information
    private void expandListOfMovies() {
        if (listOfMovies.isEmpty()) {
            System.out.println("No movies added.");
            return;
        }

        System.out.println("Your Movie List:\n");
        for (Movie currentMovie : listOfMovies) {
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
        movieCount = 0;
        lineDivider();
        System.out.println("Enter q to return to the main menu.");
        String expandButton = input.nextLine();
        while (!expandButton.equals("q")) {
            System.out.println("Incorrect input. Please try again.");
            System.out.println("Enter q to return to the main menu.");
            expandButton = input.nextLine();
        }
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

        displayUnwatchedMovies(unwatchedMovies);
    }

    // MODIFIES: this
    // EFFECTS: shows a list of all the unwatched movies by its title
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
    // EFFECTS: launch a mark and rate option in the list of watched movies
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
    public void enterRating(Movie Movie) {
        System.out.println("Please enter a rating for the movie.");
        int rateInput = input.nextInt();
        input.nextLine();
        while (rateInput < 1 || rateInput >5) {
            System.out.println("Enter a number between 1-5!");
            rateInput = input.nextInt();
            input.nextLine();
        }
        Movie.setRating(rateInput);
        Movie.markAsWatched();
        System.out.println("A rating of " + rateInput + " has been received.");
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