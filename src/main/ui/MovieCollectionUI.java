package ui;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import model.Event;
import model.EventLog;
import model.Movie;
import model.MovieCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

// Create a graphical interactive MovieCollection app that can add new movies,
// see the list of unwatched movies, mark unwatched movies as watched movies,
// and give a personal rating for their watched movies.
public class MovieCollectionUI implements ActionListener {
    private JFrame frame;
    private JLabel introLabel;
    private JButton addButton;
    private JButton viewButton;
    private JButton unwatchedButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
    private JButton expandButton;
    private JButton rateButton;
    private JButton homeButton;

    private static final String JSON_STORE = "./data/myFile.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private MovieCollection movieList;
    private JLabel imageLabel;

    /*
     * EFFECTS: constructs saving, loading, movie collection objects
     *          and buttons for expanding, rating, and returning to main page
     */
    public MovieCollectionUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        movieList = new MovieCollection("My List");
        expandButton = new JButton("Extend Movie List");
        rateButton = new JButton("Rate Movie");
        homeButton = new JButton("Return to Main Page");
        init();
    }

    /*
     * EFFECTS: constructs and handles user input on each buttons
     *          button 1 allows user to add a movie
     *          button 2 allows user to view movie list
     *          button 3 allows user to view unwatched movie list
     *          button 4 allows user to save movie list
     *          button 5 allows user to load movie list
     *          button 6 allows user to exit the application
     */
    private void init() {
        initFrame();

        JPanel topPanel = getInitImage();
        topPanel.add(introLabel);

        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(unwatchedButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(quitButton);
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(imagePanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Movie Collection");
        frame.pack();
        frame.setVisible(true);

        actionListener();
    }

    /*
     * EFFECTS: instantiates frames and buttons for the main page
     */
    private void initFrame() {
        frame = new JFrame();
        frame.setSize(550, 1200);
        frame.setLocation(500, 100);
        addButton = new JButton("Add Movie");
        viewButton = new JButton("View Movie List");
        unwatchedButton = new JButton("View Unwatched Movies");
        saveButton = new JButton("Save Movie List");
        loadButton = new JButton("Load Movie List");
        quitButton = new JButton("Exit Application");
    }

    /*
     * EFFECTS: instantiates image display and intro text for the application
     */
    private JPanel getInitImage() {
        introLabel = new JLabel("Welcome to your Movie Collection!");
        // adapted from https://www.istockphoto.com/search/2/film?phrase=movie+camera+icon
        ImageIcon imageIcon = new ImageIcon("./img/MovieIcon.jpg");
        imageLabel = new JLabel(imageIcon);
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        introLabel.setFont(new Font("Serif", Font.BOLD, 40));
        return topPanel;
    }

    /*
     * EFFECTS: creates action command for each button
     */
    public void actionListener() {
        addButton.addActionListener(this);
        addButton.setActionCommand("AddMovie");

        viewButton.addActionListener(this);
        viewButton.setActionCommand("ViewList");

        unwatchedButton.addActionListener(this);
        unwatchedButton.setActionCommand("ViewUnwatchedList");

        saveButton.addActionListener(this);
        saveButton.setActionCommand("SaveList");

        loadButton.addActionListener(this);
        loadButton.setActionCommand("LoadList");

        quitButton.addActionListener(this);
        quitButton.setActionCommand("Exit");
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates cases and close frame for several cases like adding movie
     *          viewing movie list, and viewing unwatched movie list
     */
    @Override
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "AddMovie":
                frame.dispose();
                addMovie();
                break;
            case "ViewList":
                frame.dispose();
                viewList();
                break;
            case "ViewUnwatchedList":
                frame.dispose();
                viewUnwatchedList();
                break;
            case "SaveList":
                saveList();
                break;
            case "LoadList":
                loadList();
                break;
            case "Expand":
                expandInterface();
                break;
            case "Rate":
                rateInterface();
                break;
            case "Home":
                init();
                break;
            case "Exit":
                frame.dispose();
                EventLog el = EventLog.getInstance();
                for (Event event: el) {
                    System.out.println(event.toString());
                }
                System.exit(0);
        }
    }

    /* MODIFIES: this
     * EFFECTS: creates a form for users to insert input and add the input to create a new movie
     */
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void addMovie() {
        JTextField titleField = new JTextField(5);
        JTextField directorField = new JTextField(5);
        JTextField genreField = new JTextField(5);
        JTextField durationField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(0, 2));
        myPanel.add(new JLabel("Title:"));
        myPanel.add(titleField);
        myPanel.add(new JLabel("Director:"));
        myPanel.add(directorField);
        myPanel.add(new JLabel("Genre:"));
        myPanel.add(genreField);
        myPanel.add(new JLabel("Duration (mins):"));
        myPanel.add(durationField);

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                 "Please Enter Movie Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String director = directorField.getText();
            String genre = genreField.getText();
            int duration = Integer.parseInt(durationField.getText());

            Movie newMovie = new Movie(title, director, genre, duration);
            movieList.addMovie(newMovie);

            JOptionPane.showMessageDialog(null, "Movie successfully added!");
        }
        init();
    }

    /* 
     * EFFECTS: view all the movies added by the order they were added
     *          and provided options for user to expand movie list or
     *          return back to the main page
     */
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void viewList() {
        List<Movie> movies = movieList.getMovieList();
        JFrame frame = new JFrame();
        frame.setLocation(500, 100);
        JLabel listLabel = new JLabel("Movie List:");
        listLabel.setFont(new Font("Serif", Font.BOLD, 35));
        JPanel extendPanel = new JPanel(new GridLayout(10, 2, 10, 5));
        extendPanel.setPreferredSize(new Dimension(300, 500));
        extendPanel.add(listLabel);
        for (int i = 1; i <= movies.size(); i++) {
            Movie currentMovie = movies.get(i - 1);
            JLabel movieLabel = new JLabel("Movie " + i + ": " + currentMovie.getTitle());
            movieLabel.setFont(new Font("Serif", Font.BOLD, 25));
            extendPanel.add(movieLabel);
        }
        extendPanel.add(expandButton);
        extendPanel.add(homeButton);

        extendPanel.setBackground(Color.ORANGE);

        frame.setLayout(new BorderLayout());
        frame.add(extendPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Movie Collection");
        frame.pack();
        frame.setVisible(true);

        expandButton.addActionListener(this);
        expandButton.setActionCommand("Expand");

        homeButton.addActionListener(this);
        homeButton.setActionCommand("Home");
    }

    /* 
     * EFFECTS: view all the movies added by the order they were added
     *          and shows rating for the movie that has been watched by user
     */
    private void expandInterface() {
        JFrame frame = new JFrame();
        frame.setLocation(500, 100);
        JLabel listLabel = new JLabel("Movie List:");
        listLabel.setFont(new Font("Serif", Font.BOLD, 35));
        JPanel extendPanel = new JPanel(new GridLayout(10, 2, 10, 5));
        extendPanel.setPreferredSize(new Dimension(300, 500));
        extendPanel.add(listLabel);
        getMovieList(extendPanel);
        extendPanel.add(homeButton);

        extendPanel.setBackground(Color.ORANGE);

        frame.setLayout(new BorderLayout());
        frame.add(extendPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Movie Collection");
        frame.pack();
        frame.setVisible(true);

        homeButton.addActionListener(this);
        homeButton.setActionCommand("Home");
    }

    /* 
     * EFFECTS: returns all the details information like title, director
     *          genre(, and rating if movie has been watched) for each movie
     */
    private void getMovieList(JPanel extendPanel) {
        List<Movie> movies = movieList.getMovieList();
        extendPanel.setLayout(new BoxLayout(extendPanel, BoxLayout.Y_AXIS));
        for (int i = 1; i <= movies.size(); i++) {
            Movie currentMovie = movies.get(i - 1);

            JLabel movieLabel = new JLabel("Movie " + i + ": " + currentMovie.getTitle());
            JLabel directorLabel = new JLabel("Director : " + currentMovie.getDirector());
            JLabel genreLabel = new JLabel("Genre : " + currentMovie.getGenre());
            JLabel durationLabel = new JLabel("Duration : " + currentMovie.getDuration());
            JLabel ratingLabel = new JLabel("Rating : " + currentMovie.getRating());

            movieLabel.setFont(new Font("Serif", Font.BOLD, 25));
            directorLabel.setFont(new Font("Serif", Font.BOLD, 20));
            genreLabel.setFont(new Font("Serif", Font.BOLD, 20));
            durationLabel.setFont(new Font("Serif", Font.BOLD, 20));
            ratingLabel.setFont(new Font("Serif", Font.BOLD, 20));

            extendPanel.add(movieLabel);
            extendPanel.add(directorLabel);
            extendPanel.add(genreLabel);
            extendPanel.add(durationLabel);
            if (currentMovie.getWatchedStatus() == true) {
                extendPanel.add(ratingLabel);
            }
            extendPanel.add(Box.createVerticalStrut(5));
        }
    }

    /*
     * EFFECTS: view all the unwatched movies added by the order they were added
     *          and provided options to rate a movie or return back to the main page
     */
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void viewUnwatchedList() {
        movieList.viewUnwatched();
        List<Movie> unwatchedMovies = movieList.getUnwatchedList();
        JFrame frame = new JFrame();
        frame.setLocation(500, 100);
        JLabel listLabel = new JLabel("Movie List:");
        listLabel.setFont(new Font("Serif", Font.BOLD, 35));
        JPanel extendPanel = new JPanel(new GridLayout(10, 2, 10, 5));
        extendPanel.setPreferredSize(new Dimension(300, 500));
        extendPanel.add(listLabel);
        for (int i = 1; i <= unwatchedMovies.size(); i++) {
            Movie currentMovie = unwatchedMovies.get(i - 1);
            JLabel movieLabel = new JLabel("Movie " + i + ": " + currentMovie.getTitle());
            movieLabel.setFont(new Font("Serif", Font.BOLD, 25));
            extendPanel.add(movieLabel);
        }
        extendPanel.add(rateButton);
        extendPanel.add(homeButton);

        extendPanel.setBackground(Color.ORANGE);

        frame.setLayout(new BorderLayout());
        frame.add(extendPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Movie Collection");
        frame.pack();
        frame.setVisible(true);

        rateButton.addActionListener(this);
        rateButton.setActionCommand("Rate");

        homeButton.addActionListener(this);
        homeButton.setActionCommand("Home");
    }

    /* 
     * REQUIRES: 1<= rateMovie <= movieList.size() 
     * EFFECTS: creates a frame for user to choose a movie to rate
     */
    private void rateInterface() {
        JTextField rateField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(0, 2));
        myPanel.add(new JLabel("Select a movie you want to rate: "));
        myPanel.add(rateField);

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                 "Please Enter Movie Choice", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int rateMovie = Integer.parseInt(rateField.getText());
            handleRating(rateMovie);
        }
    }

    /*
     * REQUIRES: 1 <= rating <= 5
     * MODIFIES: this
     * EFFECTS: receives a rating (1-5) and save it for a movie
     */
    private void handleRating(int rateMovie) {
        Movie movieToRate = movieList.getMovieList().get(rateMovie - 1);
        JTextField rating = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(0, 2));
        myPanel.add(new JLabel("Enter your rating (1-5): "));
        myPanel.add(rating);

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                 "Please Enter Movie Choice", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int rate = Integer.parseInt(rating.getText());
            movieToRate.markAsWatched();
            movieToRate.setRating(rate);

            JOptionPane.showMessageDialog(null, "Rating successfully added!");
        }
    }

    // EFFECTS: saves the movie collection to file
    public void saveList() {
        try {
            jsonWriter.open();
            jsonWriter.write(movieList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Data successfully saved!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads movie collection from file
    public void loadList() {
        try {
            movieList = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Data successfully loaded!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
        }
    }
}
