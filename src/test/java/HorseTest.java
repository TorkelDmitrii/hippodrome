import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;


@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    public void whenNameIsNullThenGenerateException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 2));
        assertEquals("Name cannot be null.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t"})
    public void whenNameIsBlankThenGenerateException(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 2));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void whenSpeedIsNegativeThenGenerateException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Вишня", -1, 2));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    public void whenDistanceIsNegativeThenGenerateException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Вишня", 1, -2));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    public void getName() {
        Horse horse = new Horse("Масяня", 1, 2);
        assertEquals("Масяня", horse.getName());
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("Масяня", 1, 2);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("Масяня", 1, 2);
        assertEquals(2, horse.getDistance());
    }

    @Test
    public void getDistanceWhenDistanceIsZero() {
        Horse horse = new Horse("Масяня", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void methodMoveUsesGetRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("Масяня", 1, 2).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 0.8, 0.9})
    public void methodMoveUsesGetRandomDouble(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Масяня", 1, 2);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(2 + 1 * random, horse.getDistance());
        }
    }
}
