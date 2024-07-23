// This code uses the formatting of persistence package of
// the sample term project phase 2 in CPSC 210 2024 S

package persistence;

import model.Movie;
import model.MovieCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestJsonReader extends TestJson {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MovieCollection mc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            MovieCollection mc = reader.read();
            assertEquals("My work room", mc.getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            MovieCollection mc = reader.read();
            assertEquals("Ivan's 1st file", mc.getName());
            List<Movie> movies = mc.getMovieList();
            assertEquals(2, movies.size());
            checkMovie("john wick 1", "greg", "action", 67, false, 0, movies.get(0));
            checkMovie("john wick 2", "thom", "action", 69, true, 4, movies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
