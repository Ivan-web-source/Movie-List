package ui;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

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

    private static final String JSON_STORE = "./data/myFile.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private MovieCollection movieList;
    private JLabel imageLabel;

    /*
     * EFFECTS: constructs saving, loading, movie collection objects
     */
    public MovieCollectionUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        movieList = new MovieCollection("My List");
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
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void init() {
        frame = new JFrame();
        frame.setSize(550, 1200);
        frame.setLocation(500, 100);
        introLabel = new JLabel("Welcome to your Movie Collection!");
        ImageIcon imageIcon = new ImageIcon("./img/MovieIcon.jpg");
        imageLabel = new JLabel(imageIcon);
        addButton = new JButton("Add Movie");
        viewButton = new JButton("View Movie List");
        unwatchedButton = new JButton("View Unwatched Movies");
        saveButton = new JButton("Save Movie List");
        loadButton = new JButton("Load Movie List");
        quitButton = new JButton("Exit Application");

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        introLabel.setFont(new Font("Serif", Font.BOLD, 40));
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
     * EFFECTS: creates cases and close frame for several cases like adding movie
     *          viewing movie list, and viewing unwatched movie list
     */
    @Override
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
            case "Exit":
                frame.dispose();
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
     */
    public void viewList() {
        List<Movie> movies = movieList.getMovieList();
        StringBuilder movieListStr = new StringBuilder("Movie List:\n");

        for (Movie movie : movies) {
            movieListStr.append(movie.getTitle()).append("\n");
        }

        JOptionPane.showMessageDialog(null, movieListStr.toString());
        init();
    }

    /*
     * EFFECTS: view all the unwatched movies added by the order they were added
     */
    public void viewUnwatchedList() {
        movieList.viewUnwatched();
        List<Movie> unwatchedMovies = movieList.getUnwatchedList();
        StringBuilder unwatchedListStr = new StringBuilder("Unwatched Movies:\n");

        for (Movie movie : unwatchedMovies) {
            unwatchedListStr.append(movie.getTitle()).append("\n");
        }

        JOptionPane.showMessageDialog(null, unwatchedListStr.toString());
        init();
    }

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

    public void loadList() {
        try {
            movieList = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Data successfully loaded!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
        }
    }
}
