package persistence;

import model.Book;
import model.ReadingList;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyReadingList() {
        try {
            ReadingList rl = new ReadingList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyReadingList.json");
            writer.open();
            writer.write(rl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyReadingList.json");
            rl = reader.read();
            assertEquals(0, rl.getBooks().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralReadingList() {
        try {
            ReadingList rl = new ReadingList();
            Book b1 = new Book("famous five", "sarah");
            b1.setStatus("Finished");
            b1.setGenre("fiction");
            Book b2 = new Book("then there were none", "Collen");
            b2.setStatus("To Read");
            b2.setGenre("Thriller");
            rl.addBook(b1);
            rl.addBook(b2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralReadingList.json");
            writer.open();
            writer.write(rl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralReadingList.json");
            rl = reader.read();
            List<Book> books = rl.getBooks();
            assertEquals(2, books.size());
            checkBook("famous five", "sarah", "Finished", "fiction", books.get(0));
            checkBook("then there were none", "Collen", "To Read", "Thriller", books.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
