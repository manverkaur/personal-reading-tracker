package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport

public class TestBook {
    Book testBook;
    Book testBook2;

    @BeforeEach
    void runBefore() {
        testBook = new Book("Famous Five", "Sarah");
        testBook2 = new Book("Silent Patient", "Aurthur");
    }

    @Test
    void testConstructor() {
        assertEquals("Famous Five", testBook.getTitle());
        assertEquals("Sarah", testBook.getAuthor());
        assertEquals("To Read", testBook.getStatus());
    }

    @Test
    void testsetStatus() {
        testBook.setStatus("Finished");
        assertEquals("Finished", testBook.getStatus());
    }

    @Test
    void testsetGenre() {
        testBook.setGenre("Fiction");
        assertEquals("Fiction", testBook.getGenre());

    }

    @Test
    void testIsFinished() {
        assertFalse(testBook.isFinished());
    }

    @Test
    void testIdFinishedtrue() {
        testBook.setStatus("Finished");
        assertTrue(testBook.isFinished());

    }
}
