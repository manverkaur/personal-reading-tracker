package ui;

import model.Book;
import model.ReadingList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Referenced from Lab 4: Flashcard Reviewer
// Referenced from JsonSerializationDemo
// Reading tracker application that allows user to add books and review their books
@ExcludeFromJacocoGeneratedReport
public class ReadingApp {
    private static final String JSON_STORE = "./data/readinglist.json";
    private ReadingList readingList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Scanner scanner;
    private boolean isProgramRunning;

    // EFFECTS: creates an instance of the ReadingApp console ui application
    public ReadingApp() throws FileNotFoundException {
        init();

        printDivider();
        System.out.println("Welcome to the Reading app!");
        printDivider();

        while (this.isProgramRunning) {
            handleMenu();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: initialized the application with the starting values
     */
    public void init() {
        this.readingList = new ReadingList();
        this.scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.isProgramRunning = true;
    }

    // EFFECTS: displays and process inputs for the main menu
    public void handleMenu() {
        displayMenu();
        String input = this.scanner.nextLine();
        processMenuCommand(input);
    }

    // EFFECTS: displays a list of commands that can be used in the main menu
    public void displayMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("a: Add a new book to the reading list");
        System.out.println("g: Set the genre of the book");
        System.out.println("u: Update the book's status");
        System.out.println("v: View all the books in the reading list");
        System.out.println("y: view books by status");
        System.out.println("s: save reading list to file");
        System.out.println("l: load reading list from file");
        System.out.println("q: Exit the application");
        printDivider();

    }

    // EFFECTS: process the user's input in the main menu
    public void processMenuCommand(String input) {
        printDivider();
        if (input.equals("a")) {
            addNewBook();
        } else if (input.equals("g")) {
            setGenre();
        } else if (input.equals("u")) {
            updateStatus();
        } else if (input.equals("v")) {
            viewBooks();
        } else if (input.equals("y")) {
            booksByStatus();
        } else if (input.equals("s")) {
            saveReadingList();
        } else if (input.equals("l")) {
            loadReadingList();
        } else if (input.equals("q")) {
            quitApplication();
        } else {
            System.out.println("Selection not valid...");
        }

        printDivider();
    }

    /*
     * MODIFES: this
     * EFFECTS: adds a book to the list of books
     */
    public void addNewBook() {
        System.out.println("Please enter the book's title:");
        String title = this.scanner.nextLine();

        System.out.println("\nPlease enter the book's author:");
        String author = this.scanner.nextLine();

        Book book = new Book(title, author);

        this.readingList.addBook(book);
        System.out.println("\nNew book successfully added!");
    }

    /*
     * MODIFES: this
     * EFFECTS: sets the genre of the book
     */
    public void setGenre() {
        if (isReadingListEmpty()) {
            return;
        }
        System.out.println("Please enter the book's title:");
        String title = this.scanner.nextLine();

        Book book = readingList.findBookByTitle(title);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        System.out.println("Please enter the book's genre:");
        String genre = this.scanner.nextLine();

        book.setGenre(genre);

        System.out.println("\nBook's genre is set successfully!");
    }

    /*
     * MODIFES: this
     * EFFECTS: updates the status of the book
     */
    public void updateStatus() {
        if (isReadingListEmpty()) {
            return;
        }
        System.out.println("Please enter the book's title:");
        String title = this.scanner.nextLine();

        Book book = readingList.findBookByTitle(title);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        System.out.println("Enter new status (To Read / Reading / Finished):");
        String status = this.scanner.nextLine();
        if (!status.equals("To Read")
                &&
                !status.equals("Reading")
                &&
                !status.equals("Finished")) {
            System.out.println("Invalid status.");
            return;
        }

        book.setStatus(status);

        System.out.println("\nBook's status is updated successfully!");
    }

    /*
     * MODIFIES:this
     * EFFECTS: displays all books in the list
     */
    public void viewBooks() {
        displayGivenBooks(this.readingList);
    }

    /*
     * MODIFES: this
     * EFFECTS: displays the books of given status
     */
    public void booksByStatus() {
        if (isReadingListEmpty()) {
            return;
        }
        System.out.println("Please enter the status (To Read / Reading / Finished):");
        String status = this.scanner.nextLine();

        List<Book> books = readingList.getBooksByStatus(status);

        if (books.isEmpty()) {
            System.out.println("No books found with that status");
        } else {
            for (Book b : books) {
                System.out.println(b.getTitle() + " by " + b.getAuthor());
            }
        }

    }

    /*
     * MODIFES: this
     * EFFECTS: displays the given list of books
     */
    public void displayGivenBooks(ReadingList readingList) {
        List<Book> books = readingList.getBooks();
        if (books.isEmpty()) {
            System.out.println("Error: No books to view. Try adding books first!");
            return;
        }
        for (Book b : books) {
            System.out.println("Title: " + b.getTitle());
            System.out.println("Author: " + b.getAuthor());
            System.out.println("Status: " + b.getStatus());
            System.out.println("Genre: " + b.getGenre());
            printDivider();
        }

    }

    // EFFECTS: saves the reading list to file
    private void saveReadingList() {
        try {
            jsonWriter.open();
            jsonWriter.write(readingList);
            jsonWriter.close();
            System.out.println("Reading List saved successfully to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: loads readinglist from file
     */
    private void loadReadingList() {
        try {
            readingList = jsonReader.read();
            System.out.println("Reading list loaded sucessfully from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: prints a closing message and marks the program as not running
     */
    public void quitApplication() {
        System.out.println("Thanks for using the Reading app!");
        System.out.println("Have a good day!");
        this.isProgramRunning = false;
    }

    // EFFECTS: checks if reading list is empty and prints message if so
    // returns true if empty, false otherwise
    private boolean isReadingListEmpty() {
        if (readingList.getBooks().isEmpty()) {
            System.out.println("No books in the reading list yet. Try adding some first!");
            return true;
        }
        return false;
    }

    // EFFECTS: prints out a line of dashes to act as a divider
    private void printDivider() {
        System.out.println("------------------------------------");
    }

}
