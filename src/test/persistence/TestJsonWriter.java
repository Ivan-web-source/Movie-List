// This code uses the formatting of persistence package of
// the sample term project phase 2 in CPSC 210 2024 S

package persistence;

import model.Movie;
import model.MovieCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestJsonWriter extends TestJson {

    @Test
    void testWriterInvalidFile() {
        try {
            MovieCollection mc = new MovieCollection("The work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            MovieCollection mc = new MovieCollection("The work room");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(mc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            mc = reader.read();
            assertEquals("The work room", mc.getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            MovieCollection mc = new MovieCollection("My work room");
            mc.addMovie(new Movie("Thomas", "Kevin", "Sci-fi", 99));
            mc.addMovie(new Movie("Avenger", "Samuel", "Action", 127));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(mc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            mc = reader.read();
            assertEquals("My work room", mc.getName());
            List<Movie> movies = mc.getMovieList();
            assertEquals(2, movies.size());
            checkMovie("Thomas", "Kevin", "Sci-fi", 99, false, 0, movies.get(0));
            checkMovie("Avenger", "Samuel", "Action", 127, false, 0, movies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}