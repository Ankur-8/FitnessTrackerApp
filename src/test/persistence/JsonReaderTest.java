package persistence;

import model.JogList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

//    @BeforeEach
//    void setup() {
//        JsonWriter writer1 = new JsonWriter("./data/readerEmptyListTest.json");
//        JsonWriter writer2 = new JsonWriter("./data/readerNormalListTest.json");
//        try {
//            writer1.open();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            writer2.open();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    @Test
    void readerNonExistentFileTest() {
        JsonReader reader = new JsonReader("./data/readerNonExistentFileTest.json");
        try {
            JogList jogList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // Test passes
        }
    }

    @Test
    void readerEmptyListTest() {
        JsonReader reader = new JsonReader("./data/readerEmptyListTest.json");
        try {
            JogList jogList = reader.read();
            assertEquals(0, jogList.jogListSize());
        } catch (IOException e) {
            fail("Test failed");
        }
    }

    @Test
    void readerNormalListTest() {
        JsonReader reader = new JsonReader("./data/readerNormalListTest.json");
        try {
            JogList jogList = reader.read();
            assertEquals(2, jogList.jogListSize());
            checkJog(1, jogList.getJog(0));
            checkJog(2, jogList.getJog(1));

        } catch (IOException e) {
            fail("Test Failed");
        }
    }
}
