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

class HashArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(1, (Integer.valueOf(1)).hashCode() % 15),
                Arguments.of(0, (Integer.valueOf(0)).hashCode() % 15),
                Arguments.of(1 << 31 - 1, (Integer.valueOf(1 << 31 - 1)).hashCode() % 15),
                Arguments.of(13, (Integer.valueOf(13)).hashCode() % 15),
                Arguments.of(128, (Integer.valueOf(128)).hashCode() % 15),
                Arguments.of(595959, (Integer.valueOf(595959)).hashCode() % 15),
                Arguments.of(-100000, (Integer.valueOf(-100000)).hashCode() % 15)
        );
    }
}

public class MapTest {
    private HashMap<Integer, Integer> map;

    @BeforeEach
    void initMap() {
        map = new HashMap<>(15);
    }

    @ParameterizedTest
    @DisplayName("Check hash correctness")
    @ArgumentsSource(HashArgumentsProvider.class)
    void hashTest(int key, int result) {
        assertEquals(result, map.hash(key));
    }

    @ParameterizedTest
    @DisplayName("Check simple insert")
    @ArgumentsSource(MapArgumentsProvider.class)
    void simpleInsert(int k1, int v1) {
        assertTrue(map.insert(k1, v1));
    }

    @ParameterizedTest
    @DisplayName("Check simple erase")
    @ArgumentsSource(MapArgumentsProvider.class)
    void simpleEraseFalse(int k1, int v1) {
        assertFalse(map.erase(k1));
        assertFalse(map.erase(v1));
    }

    @Test
    @DisplayName("Check size correctness")
    void sizeTest() {
        assertEquals(0, map.size());

        int size = 50;
        for (int i = 0; i < size; ++i) {
            assertTrue(map.insert(i, i));
            assertEquals(i + 1, map.size());
        }

        for (int i = 0; i < size; ++i) {
            assertTrue(map.erase(i));
            assertEquals(size - i - 1, map.size());
        }

    }

    @Test
    @DisplayName("Random operations")
    void big() {
        int size = 1000;
        for (int i = 0; i < size; i += 2) {
            assertTrue(map.insert(i, i));
        }

        for (int i = 0; i < size; ++i) {
            if (i % 2 == 0) {

                Optional<Integer> found = map.find(i);
                assertTrue(found.isPresent(), "not found " + i);
                assertEquals(i, (int) found.get());
                assertTrue(map.erase(i), "not erased " + i);

            } else {
                assertFalse(map.find(i).isPresent());
                assertFalse(map.erase(i));
            }
        }

        for (int i = 0; i < size; ++i) {
            assertFalse(map.erase(i));
        }
    }

}
