package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JogListTest {

    private JogList jogList;
    private Jog jog1;
    private Jog jog2;
    private Jog jog3;

    @BeforeEach
    void setup(){
        jogList = new JogList();
        jog1 = new Jog(1);
        jog2 = new Jog(2);
        jog3 = new Jog(3);

    }

    @Test
    void JogListTest(){
        assertEquals(0, jogList.jogListSize());
    }

    @Test
    void addJogTest(){
        assertEquals(0, jogList.jogListSize());
        jogList.addJog(jog1);
        assertEquals(1, jogList.jogListSize());
        assertEquals(jog1, jogList.searchJog(1));
        jogList.addJog(jog2);
        assertEquals(2, jogList.jogListSize());
        assertEquals(jog2, jogList.searchJog(2));
    }

    @Test
    void removeJogTest(){
        jogList.addJog(jog1);
        jogList.addJog(jog2);
        assertEquals(2, jogList.jogListSize());
        jogList.removeJog(1);
        assertEquals(1, jogList.jogListSize());
    }

    @Test
    void searchJogTest(){
        jogList.addJog(jog1);
        jogList.addJog(jog2);
        jogList.addJog(jog3);
        assertEquals(jog3, jogList.searchJog(3));
        assertEquals(jog1, jogList.searchJog(1));
        assertEquals(null, jogList.searchJog(4));
    }

    @Test
    void jogListSizeTest(){
        jogList.addJog(jog1);
        assertEquals(1, jogList.jogListSize());
        jogList.addJog(jog2);
        assertEquals(2, jogList.jogListSize());
    }

    @Test void getJogListTest() {
        ArrayList temp = new ArrayList<>();
        jogList.addJog(jog1);
        temp.add(jog1);
        assertEquals(temp, jogList.getJogList());
    }
}
