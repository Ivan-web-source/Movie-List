package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMovieCollection {
    private MovieCollection testMovieList;
    private Movie testMovie1;
    private Movie testMovie2;
    private Movie testMovie3;

    @BeforeEach
    void runBefore() {
        testMovieList = new MovieCollection();
        testMovie1 = new Movie("John Wick", "Cilian Murphy", "Action", 120);
        testMovie2 = new Movie("John Wick 2", "Kylian Mbappe", "Romance", 60);
        testMovie3 = new Movie("John Wick 3", "Natasha Romanoff", "Sci-fi", 127);
    }

    @Test
    void testConstructor() {
        ArrayList<Movie> testEmptyList = new ArrayList<>();
        assertEquals(testEmptyList, testMovieList.getMovieList());
        assertEquals(testEmptyList, testMovieList.getUnwatchedList());
    }

    @Test
    void testAddMovie() {
        testMovieList.addMovie(testMovie1);
        ArrayList<Movie> testList = new ArrayList<>();
        testList.add(testMovie1);
        assertEquals(testList, testMovieList.getMovieList());
        testMovieList.addMovie(testMovie2);
        testList.add(testMovie2);
        assertEquals(testList, testMovieList.getMovieList());
        testMovieList.addMovie(testMovie3);
        testList.add(testMovie3);
        assertEquals(testList, testMovieList.getMovieList());
    }

    @Test
    void testViewUnwatched() {
        testMovieList.addMovie(testMovie1);
        ArrayList<Movie> testList = new ArrayList<>();
        testList.add(testMovie1);
        testMovieList.viewUnwatched();
        assertEquals(testList, testMovieList.getUnwatchedList());
    }

    @Test
    void testViewUnwatchedMarkAsWatch() {
        ArrayList<Movie> testList = new ArrayList<>();
        testMovie2.markAsWatched();
        testMovieList.addMovie(testMovie2);
        testMovieList.viewUnwatched();
        assertEquals(testList, testMovieList.getUnwatchedList());
    }
}
