package persistence;

import model.Book;
import model.ReadingList;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ReadingList rl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyReadingList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyReadingList.json");
        try {
            ReadingList rl = reader.read();
            assertEquals(0, rl.getBooks().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralReadingList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralReadingList.json");
        try {
            ReadingList rl = reader.read();
            List<Book> books = rl.getBooks();
            assertEquals(2, books.size());
            checkBook("then there were none", "Collen", "To Read", "mystery", books.get(0));
            checkBook("famous five", "sarah", "Finished", "fiction", books.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
