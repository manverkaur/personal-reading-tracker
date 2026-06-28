package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;

// Represents a collection of Books

public class ReadingList implements Writable {
    private List<Book> books;

    // EFFECTS: creates a readinglist with an empty list of books
    public ReadingList() {
        books = new ArrayList<>();
    }

    /*
     * MODOIFIES: this
     * EFFECTS: adds a book to list if it is not already in the list
     */
    public void addBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
            EventLog.getInstance().logEvent(new Event("Book " + book.getTitle() + " added to reading list."));
        }

    }

    /*
     * MODIFIES: this
     * EFFECTS: find a book with given title or returns null if no such book exists
     */
    public Book findBookByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().equals(title)) {
                return b;

            }
        }
        return null;
    }

    // EFFECTS: returns the list of all the books
    public List<Book> getBooks() {
        return books;
    }

    /*
     * EFFECTS: returns the list of all the books which matches the given status,
     * in the order in which they were added,
     * returns an empty list if there is no such book
     */
    public List<Book> getBooksByStatus(String status) {
        List<Book> booksByStatus = new ArrayList<>();
        for (Book b : books) {
            if (b.getStatus().equals(status)) {
                booksByStatus.add(b);

            }

        }
        return booksByStatus;

    }

    // EFFECTS: returns the total number of finished books
    public int countFinishedBooks() {
        int count = 0;
        for (Book b : books) {
            if (b.getStatus().equals("Finished")) {
                count++;
            }

        }
        return count;

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("books", booksToJson());
        return json;
    }

    // EFFECTS: returns books in this readinglist as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : books) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }

}
