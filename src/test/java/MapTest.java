import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import task2.HashMap;

import java.util.Optional;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class MapArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(1, 4),
                Arguments.of(2, 5),
                Arguments.of(3, 6)
        );
    }
}

public class MapTest {
    private HashMap<Integer, Integer> map;

    @BeforeEach
    void initMap() {
        map = new HashMap<>(5);
    }

    @ParameterizedTest
    @DisplayName("Check simple insert")
    @ArgumentsSource(MapArgumentsProvider.class)
    void SimpleInsert(int k1, int v1) {
        assertTrue(map.Insert(k1, v1));
    }

    @ParameterizedTest
    @DisplayName("Check simple erase")
    @ArgumentsSource(MapArgumentsProvider.class)
    void SimpleEraseFalse(int k1, int v1) {
        assertFalse(map.Erase(k1));
        assertFalse(map.Erase(v1));
    }

    @Test
    @DisplayName("Check size correctness")
    void sizeTest() {

        assertEquals(0, map.Size());

        int size = 50;
        for (int i = 0; i < size; ++i) {
            assertTrue(map.Insert(i, i));
            assertEquals(i + 1, map.Size());
        }

        for (int i = 0; i < size; ++i) {
            assertTrue(map.Erase(i));
            assertEquals(size - i - 1, map.Size());
        }

    }

    @Test
    @DisplayName("Random operations")
    void Big() {

        int size = 1000;
        for (int i = 0; i < size; i += 2) {
            assertTrue(map.Insert(i, i));
        }

        for (int i = 0; i < size; ++i) {
            if (i % 2 == 0) {

                Optional<Integer> found = map.Find(i);
                assertTrue(found.isPresent(), "not found " + i);
                assertEquals(i, (int) found.get());
                assertTrue(map.Erase(i), "not erased " + i);

            } else {
                assertFalse(map.Find(i).isPresent());
                assertFalse(map.Erase(i));
            }
        }

        for (int i = 0; i < size; ++i) {
            assertFalse(map.Erase(i));
        }
    }
}
