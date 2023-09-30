package persistence;

import model.Jog;
import model.JogList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void writerInvalidFileTest() {
        try {
            JogList jogList = new JogList();
            JsonWriter writer = new JsonWriter("./data/my\0mistake:name.json");
            writer.open();
            fail("Test failed");
        } catch (FileNotFoundException e) {
            //Test passes
        }
    }

    @Test
    void writerEmptyListTest() {
        try {
            JogList jogList = new JogList();
            JsonWriter writer = new JsonWriter("./data/writerEmptyListTest.json");
            writer.open();
            writer.write(jogList);
            writer.close();

            JsonReader reader = new JsonReader("./data/writerEmptyListTest.json");
            jogList = reader.read();
            assertEquals(0,jogList.jogListSize());
        } catch (IOException e) {
            fail("No exception needed");
        }
    }

    @Test
    void writerNormalListTest() {
        try {
            JogList jogList = new JogList();
            jogList.addJog(new Jog(1,1000,10,5,70, "july:31"));
            jogList.addJog(new Jog(2,1500,20,10,75, "july:30"));
            JsonWriter writer = new JsonWriter("./data/writerNormalListTest.json");
            writer.open();
            writer.write(jogList);
            writer.close();

            JsonReader reader = new JsonReader("./data/writerNormalListTest.json");
            jogList = reader.read();
            assertEquals(2,jogList.jogListSize());
            checkJog(1,jogList.getJog(0));
            checkJog(2,jogList.getJog(1));
        } catch (IOException e) {
            fail("No exception needed");
        }
    }
}
