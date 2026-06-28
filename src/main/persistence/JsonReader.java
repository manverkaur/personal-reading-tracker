package persistence;

import model.Book;
import model.ReadingList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Referenced from JsonSerializationDemo
// Represents a reader that reads readinglist from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads readinglist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ReadingList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseReadingList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses readinglist from JSON object and returns it
    private ReadingList parseReadingList(JSONObject jsonObject) {
        ReadingList rl = new ReadingList();
        addBooks(rl, jsonObject);
        return rl;
    }

    // MODIFIES: rl
    // EFFECTS: parses books from JSON object and adds them to readinglist
    private void addBooks(ReadingList rl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(rl, nextBook);
        }
    }

    // MODIFIES: rl
    // EFFECTS: parses book from JSON object and adds it to readinglist
    private void addBook(ReadingList rl, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        String status = jsonObject.getString("status");
        String genre = jsonObject.optString("genre", "Unknown");

        Book book = new Book(title, author);
        book.setStatus(status);
        book.setGenre(genre);
        rl.addBook(book);
    }
}
