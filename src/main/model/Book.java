package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a book having a title, author, genre and reading Status

public class Book implements Writable {
    private String title;
    private String author;
    private String genre;
    private String status; // "To Read","Reading", "Finished"

    /*
     * REQUIRES: title, author, and genre are non-empty strings;
     * status is one of: "To Read","Reading", "Finished"
     * EFFECTS: create a book with given title, author
     * status is set to "To Read" as default
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.status = "To Read";

    }

    /*
     * REQUIRES: status is one of: "To Read","Reading", "Finished"
     * MODIFIES: this
     * EFFECTS: updates the reading status of this book
     */
    public void setStatus(String status) {
        this.status = status;
        EventLog.getInstance().logEvent(new Event("Status of book " + title + " updated to " + status + "."));
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets the genre of the book
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    // EFFECTS: returns true if book's status is "Finished", false otherwise
    public boolean isFinished() {
        if (getStatus().equals("Finished")) {
            return true;
        } else {
            return false;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("status", status);
        json.put("genre", genre);
        return json;
    }
}
