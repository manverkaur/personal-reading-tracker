package ui;

import model.Book;
import model.Event;
import model.EventLog;
import model.ReadingList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

/*
 * Represents the main graphical user interface window for the Reading Tracker application.
 * Allow users to add books, update their reading status, and save/load the reading list.
 */
@ExcludeFromJacocoGeneratedReport

public class ReadingListGUI extends JFrame implements ActionListener, WindowListener {
    private static final String JSON_STORE = "./data/readinglist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private ReadingList rl;
    private BookPanel bp;
    private JButton addBookButton;
    private JButton setStatusButton;
    private JButton saveButton;
    private JButton loadButton;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField statusTitleField;
    private JTextField statusField;
    private ImageIcon bookImage;
    private JLabel imageLabel;
    private JPanel imagePanel;

    // Constructs main window
    // EFFECTS: initialize the GUI and displays the main application window
    public ReadingListGUI() {
        super("Reading Tracker");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(this);

        initializeFields();
        initializeImage();
        intializeButtons();

        setLayout(new BorderLayout());

        add(bp, BorderLayout.CENTER);

        imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        add(imagePanel, BorderLayout.NORTH);

        createInputPanel();

        pack();
        centreOnScreen();
        setVisible(true);
    }

    // Centres frame on desktop
    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes the reading list, text fields, and buttons
     */

    public void initializeFields() {

        rl = new ReadingList();
        bp = new BookPanel(rl);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        titleField = new JTextField(5);
        authorField = new JTextField(5);
        statusTitleField = new JTextField(5);
        statusField = new JTextField(5);

        addBookButton = new JButton("Add Book");
        setStatusButton = new JButton("Set Status");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and adds a panel containing input fields and buttons to the
     * frame
     */
    private void createInputPanel() {

        JPanel inputPanel = new JPanel();

        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);

        inputPanel.add(addBookButton);

        inputPanel.add(new JLabel("Book Title"));
        inputPanel.add(statusTitleField);

        inputPanel.add(new JLabel("New Status"));
        inputPanel.add(statusField);

        inputPanel.add(setStatusButton);

        inputPanel.add(saveButton);
        inputPanel.add(loadButton);

        add(inputPanel, BorderLayout.SOUTH);
    }

    /*
     * MODIFIES: this
     * EFFECTS: processes user actions by adding books, updating status, saving
     * or loading the reading list
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("addBook")) {

            String title = titleField.getText();
            String author = authorField.getText();

            rl.addBook(new Book(title, author));

            bp.update(rl);

            imageLabel.setVisible(true);

            titleField.setText("");
            authorField.setText("");
        } else if (e.getActionCommand().equals("setStatus")) {
            String title = statusTitleField.getText();
            String status = statusField.getText();

            Book book = rl.findBookByTitle(title);

            if (book != null) {
                book.setStatus(status);
                bp.update(rl);
            }

            statusTitleField.setText("");
            statusField.setText("");

        } else if (e.getActionCommand().equals("save")) {
            saveReadingList();

        } else if (e.getActionCommand().equals("load")) {
            loadReadingList();
        }

    }

    // EFFECTS: saves the reading list to file
    private void saveReadingList() {
        try {
            jsonWriter.open();
            jsonWriter.write(rl);
            jsonWriter.close();
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
            rl = jsonReader.read();
            bp.update(rl);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: loads, resizes, and initialized the book image label
     */
    public void initializeImage() {
        ImageIcon originalIcon = new ImageIcon("images/book.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        bookImage = new ImageIcon(scaledImage);
        imageLabel = new JLabel(bookImage);
        imageLabel.setVisible(false);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets actions commands and attaches listeners to all buttons
     */
    public void intializeButtons() {
        addBookButton.setActionCommand("addBook");
        addBookButton.addActionListener(this);

        setStatusButton.setActionCommand("setStatus");
        setStatusButton.addActionListener(this);

        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);

        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);
    }

    public static void main(String[] args) {
        new ReadingListGUI();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        printLog();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    /*
     * EFFECTS: prints to the console all events that have been logged
     * in the EventLog since the application started
     */
    public void printLog() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString());

        }
    }

}
