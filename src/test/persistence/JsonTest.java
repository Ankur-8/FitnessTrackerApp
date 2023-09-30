package persistence;

import model.Jog;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkJog(int jogId, Jog jog) {
        assertEquals(jogId, jog.getJogId());
    }
}
