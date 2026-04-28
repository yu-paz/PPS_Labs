import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Part 2: A JUnit test that fails if ANY value in an int array is less than 20.
 *
 * Strategy: walk the array and assertTrue on each element. The first value
 * below 20 will fail the assertion (with a useful message telling us which
 * index and value caused the failure) and the test stops there.
 */
class ArrayMinimumTest {

    @Test
    void allValuesShouldBeAtLeast20() {
        int[] values = { 25, 42, 30, 99, 20, 73 };

        for (int i = 0; i < values.length; i++) {
            assertTrue(values[i] >= 20,
                "Value at index " + i + " was " + values[i] + " (must be >= 20)");
        }
    }

    /*
     * Demonstration that the test correctly FAILS when an element is too small.
     * Uncomment the @Test annotation below to see it fail.
     */
    // @Test
    void allValuesShouldBeAtLeast20_failingExample() {
        int[] values = { 25, 42, 19, 99 }; // 19 is below the threshold

        for (int i = 0; i < values.length; i++) {
            assertTrue(values[i] >= 20,
                "Value at index " + i + " was " + values[i] + " (must be >= 20)");
        }
    }
}
