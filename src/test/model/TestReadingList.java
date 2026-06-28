package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TestReadingList {
    Book testBook;
    Book testBook2;
    ReadingList testReadingList;

    @BeforeEach
    void runBefore() {
        testReadingList = new ReadingList();
        testBook = new Book("Famous Five", "Sarah");
        testBook2 = new Book("Silent Patient", "Aurthur");

    }

    @Test
    void testConstructor() {
        assertEquals(0, testReadingList.getBooks().size());
    }

    @Test
    void testAddBook() {
        testReadingList.addBook(testBook);
        testReadingList.addBook(testBook2);
        assertEquals(2, testReadingList.getBooks().size());
        assertEquals(testBook, testReadingList.getBooks().get(0));
        assertEquals(testBook2, testReadingList.getBooks().get(1));

    }

    @Test
    void testDuplicateBook() {
        testReadingList.addBook(testBook);
        testReadingList.addBook(testBook);
        assertEquals(1, testReadingList.getBooks().size());
        assertEquals(testBook, testReadingList.getBooks().get(0));
    }

    @Test
    void testfindBook() {
        testReadingList.addBook(testBook);
        testReadingList.addBook(testBook2);
        assertEquals(testBook2, testReadingList.findBookByTitle("Silent Patient"));
    }

    @Test
    void testFindbookNull() {
        testReadingList.addBook(testBook);
        testReadingList.addBook(testBook2);
        assertNull(testReadingList.findBookByTitle("Silent"));

    }

    @Test
    void testGetBooksbyStatusNull() {
        testReadingList.addBook(testBook);
        testReadingList.addBook(testBook2);
        assertEquals(0, testReadingList.getBooksByStatus("Finished").size());

    }

    @Test
    void testGetBooksByStatus() {
        testReadingList.addBook(testBook);
        testReadingList.addBook(testBook2);
        assertEquals(2, testReadingList.getBooksByStatus("To Read").size());
        assertEquals(testBook, testReadingList.getBooksByStatus("To Read").get(0));
        assertEquals(testBook2, testReadingList.getBooksByStatus("To Read").get(1));

    }

    @Test
    void testCount() {
        testReadingList.addBook(testBook);
        testReadingList.addBook(testBook2);
        assertEquals(0, testReadingList.countFinishedBooks());
    }

    @Test
    void tesCountFinished() {
        testReadingList.addBook(testBook);
        testReadingList.addBook(testBook2);
        testBook.setStatus("Finished");
        assertEquals(1, testReadingList.countFinishedBooks());

    }

}
