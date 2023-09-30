package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JogTest {

    private Jog jog1;
    private Jog jog2;

    @BeforeEach
    void setup() {
        jog1 = new Jog(1);
        jog2 = new Jog(2);
        jog2.setDistanceRan(1800);
        jog2.setTimeTaken(10);
        jog2.setAvgSpeed(3);
        jog2.setAvgHeartRate(82);
    }

    @Test
    void JogTest(){
        assertEquals(1, jog1.getJogId());
        assertEquals(0, jog1.getDistanceRan());
        assertEquals(0, jog1.getTimeTaken());
        assertEquals(0, jog1.getAvgSpeed());
        assertEquals(0, jog1.getAvgHeartRate());
        assertEquals("To be assigned", jog1.getDateOfJog());
    }

    @Test
    void setDistanceRanTest(){
        assertTrue(jog1.setDistanceRan(1200));
        assertEquals(1200, jog1.getDistanceRan());
    }

    @Test
    void setTimeTakenTest(){
        assertTrue(jog1.setTimeTaken(4));
        assertEquals(4, jog1.getTimeTaken());
    }

    @Test
    void setAvgSpeedTest(){
        assertTrue(jog1.setAvgSpeed(5));
        assertEquals(5, jog1.getAvgSpeed());
    }

    @Test
    void setAvgHeartRate(){
        assertTrue(jog1.setAvgHeartRate(78));
        assertEquals(78, jog1.getAvgHeartRate());
    }

    @Test
    void setDateOfJog(){
        assertTrue(jog1.setDateOfJog("July:21"));
        assertEquals("July:21", jog1.getDateOfJog());
    }

    @Test
    void getJogIdTest(){
        assertEquals(2, jog2.getJogId());
    }

    @Test
    void getDistanceRanTest(){
        assertEquals(1800, jog2.getDistanceRan());
    }

    @Test
    void getTimeTakenTest(){
        assertEquals(10, jog2.getTimeTaken());
    }

    @Test
    void getAvgSpeedTest(){
        assertEquals(3, jog2.getAvgSpeed());
    }

    @Test
    void getAvgHeartRateTest(){
        assertEquals(82, jog2.getAvgHeartRate());
    }
}
