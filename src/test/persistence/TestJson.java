// This code uses the formatting of persistence package of
// the sample term project phase 2 in CPSC 210 2024 S

package persistence;

import model.Movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestJson {
    protected void checkMovie(String title, String director, String genre, int duration,
            boolean watchedStatus, int rating, Movie newMovie) {
        assertEquals(title, newMovie.getTitle());
        assertEquals(director, newMovie.getDirector());
        assertEquals(genre, newMovie.getGenre());
        assertEquals(duration, newMovie.getDuration());
        assertEquals(watchedStatus, newMovie.getWatchedStatus());
        assertEquals(rating, newMovie.getRating());
    }
}
