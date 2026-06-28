package ui;

import model.Book;
import model.ReadingList;

import javax.swing.*;
import java.awt.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

/*
 * Represents the panel in which all books in the reading list are displayed.
 */
@ExcludeFromJacocoGeneratedReport

public class BookPanel extends JPanel {

    private ReadingList readingList;
    private JTextArea bookDisplay;
    private JLabel titleLabel;

    // constructs a book panel
    // EFFECTS: sets the background colour and creates a display area
    // for showing all books currently in the reading list
    public BookPanel(ReadingList rl) {
        readingList = rl;

        setBackground(new Color(220, 220, 220));
        setLayout(new BorderLayout());
        titleLabel = new JLabel("My Reading List");
        add(titleLabel, BorderLayout.NORTH);

        bookDisplay = new JTextArea();
        bookDisplay.setEditable(false);

        add(new JScrollPane(bookDisplay), BorderLayout.CENTER);

        update(rl);
    }

    // updates the book panel
    // MODIFIES: this
    // EFFECTS: updates the display to show all books currently in the reading list
    public void update(ReadingList rl) {

        bookDisplay.setText("");

        for (Book b : rl.getBooks()) {
            bookDisplay.append(
                    b.getTitle() + " - "
                            + b.getAuthor() + " - "
                            + b.getStatus() + "\n");
        }

        repaint();
    }
}