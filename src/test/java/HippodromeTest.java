import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class HippodromeTest {
    @Test
    void whenListIsNullThenGenerateException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    void whenListIsEmptyThenGenerateException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void getHorses() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse("Масяня" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(horseList, hippodrome.getHorses());
    }

    @Test
    public void moveTest() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for (Horse horse : horseList) {
            Mockito.verify(horse).move();
        }

    }

    @Test
    public void getWinner() {
        List<Horse> horseList = new ArrayList<>();
        Horse horse1 =new Horse("horse1", 2,4);
        Horse horse2 =new Horse("horse2", 2,5);
        Horse horse3 =new Horse("horse1", 2,7);
        Horse horse4 =new Horse("horse1", 2,9);
        horseList.add(horse1);
        horseList.add(horse2);
        horseList.add(horse3);
        horseList.add(horse4);

        Hippodrome hippodrome = new Hippodrome(horseList);
        assertSame(horse4,hippodrome.getWinner());
    }
}
