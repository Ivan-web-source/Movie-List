package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestMovie {
    private Movie testMovie1;
    
    @BeforeEach
    void runBefore() {
        testMovie1 = new Movie("John Wick 1", "Cilian Murphy", "Action", 120);
    }

    @Test
    void testConstructor() {
        assertEquals("John Wick 1", testMovie1.getTitle());
        assertEquals("Cilian Murphy", testMovie1.getDirector());
        assertEquals("Action", testMovie1.getGenre());
        assertEquals(120, testMovie1.getDuration());
        assertFalse(testMovie1.getWatchedStatus());
        assertEquals(0, testMovie1.getRating());
    }

    @Test
    void testSetRating() {
        testMovie1.setRating(4);
        assertEquals(4, testMovie1.getRating());
    }

    @Test
    void testSetRatingMultiple() {
        testMovie1.setRating(4);
        testMovie1.setRating(2);
        assertEquals(2, testMovie1.getRating());
    }

    @Test 
    void testMarkAsWatched() {
        testMovie1.markAsWatched();
        assertTrue(testMovie1.getWatchedStatus());
    }

    @Test 
    void testMarkAsWatchedMultiple() {
        testMovie1.markAsWatched();
        testMovie1.markAsWatched();
        assertTrue(testMovie1.getWatchedStatus());
    }
}
