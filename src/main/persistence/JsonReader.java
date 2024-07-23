// This code uses the formatting of persistence package of
// the sample term project phase 2 in CPSC 210 2024 S

package persistence;

import model.Movie;
import model.MovieCollection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads movie collection from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MovieCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCollection(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses movie collection from JSON object and returns it
    private MovieCollection parseCollection(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MovieCollection mc = new MovieCollection(name);
        addMovies(mc, jsonObject);
        return mc;
    }

    // MODIFIES: mc
    // EFFECTS: parses thingies from JSON object and adds them to movie collection
    private void addMovies(MovieCollection mc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movies");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addMovie(mc, nextThingy);
        }
    }

    // MODIFIES: mc
    // EFFECTS: parses thingy from JSON object and adds it to movie collection
    private void addMovie(MovieCollection mc, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String director = jsonObject.getString("director");
        String genre = jsonObject.getString("genre");
        int duration = jsonObject.getInt("duration");
        boolean watchedStatus = jsonObject.getBoolean("watch status");
        int rating = jsonObject.getInt("rating");
        Movie newMovie = new Movie(title, director, genre, duration);
        if (watchedStatus) {
            newMovie.markAsWatched();
            newMovie.setRating(rating);
        } 
        mc.addMovie(newMovie);
    }
}
