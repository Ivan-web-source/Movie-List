package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMovie {
    private Movie testMovie;
    private List<Movie> testMovieList;
    
    @BeforeEach
    void runBefore() {
        testMovieList = new ArrayList<>();
        testMovie = new Movie();
    }

    @Test
    void constructorTest() {
        assertTrue(true);
    }
}
