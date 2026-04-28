import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Part 5: Comprehensive JUnit tests for the Computations class.
 * Each method gets several tests covering normal cases AND edge cases.
 */
class ComputationsTest {

    // ============================================================
    // fibonacci(int n)
    // ============================================================

    @Test
    @DisplayName("fibonacci(0) is 0  (edge case)")
    void fibonacciOfZero() {
        assertEquals(0, Computations.fibonacci(0));
    }

    @Test
    @DisplayName("fibonacci(1) is 1  (edge case)")
    void fibonacciOfOne() {
        assertEquals(1, Computations.fibonacci(1));
    }

    @Test
    @DisplayName("fibonacci of small values matches the known sequence")
    void fibonacciSmallValues() {
        // Sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55
        assertEquals(1,  Computations.fibonacci(2));
        assertEquals(2,  Computations.fibonacci(3));
        assertEquals(3,  Computations.fibonacci(4));
        assertEquals(5,  Computations.fibonacci(5));
        assertEquals(8,  Computations.fibonacci(6));
        assertEquals(13, Computations.fibonacci(7));
        assertEquals(55, Computations.fibonacci(10));
    }

    @Test
    @DisplayName("fibonacci of larger value (15) is 610")
    void fibonacciLargerValue() {
        assertEquals(610, Computations.fibonacci(15));
    }

    @Test
    @DisplayName("fibonacci of negative input throws IllegalArgumentException")
    void fibonacciNegativeThrows() {
        assertThrows(IllegalArgumentException.class,
                     () -> Computations.fibonacci(-1));
        assertThrows(IllegalArgumentException.class,
                     () -> Computations.fibonacci(-100));
    }

    // ============================================================
    // isPrime(int n)
    // ============================================================

    @Test
    @DisplayName("isPrime: numbers <= 1 are NOT prime  (edge cases)")
    void isPrimeEdgeCasesBelowTwo() {
        assertFalse(Computations.isPrime(1));
        assertFalse(Computations.isPrime(0));
        assertFalse(Computations.isPrime(-1));
        assertFalse(Computations.isPrime(-7));   // negative number that would otherwise be prime
        assertFalse(Computations.isPrime(Integer.MIN_VALUE));
    }

    @Test
    @DisplayName("isPrime: 2 is prime  (smallest prime - edge case)")
    void isPrimeOfTwo() {
        assertTrue(Computations.isPrime(2));
    }

    @Test
    @DisplayName("isPrime: known small primes return true")
    void isPrimeKnownPrimes() {
        int[] primes = { 3, 5, 7, 11, 13, 17, 19, 23, 29, 97, 101 };
        for (int p : primes) {
            assertTrue(Computations.isPrime(p), p + " should be prime");
        }
    }

    @Test
    @DisplayName("isPrime: composites return false")
    void isPrimeComposites() {
        int[] composites = { 4, 6, 8, 9, 10, 15, 21, 25, 49, 100 };
        for (int c : composites) {
            assertFalse(Computations.isPrime(c), c + " should NOT be prime");
        }
    }

    @Test
    @DisplayName("isPrime: perfect squares of primes are composite")
    void isPrimePerfectSquaresAreNotPrime() {
        // 9 = 3*3, 25 = 5*5, 49 = 7*7 - good edge cases for the
        // sqrt-bound loop in the implementation
        assertFalse(Computations.isPrime(9));
        assertFalse(Computations.isPrime(25));
        assertFalse(Computations.isPrime(49));
        assertFalse(Computations.isPrime(121));
    }

    // ============================================================
    // isEven(int number)
    // ============================================================

    @Test
    @DisplayName("isEven: 0 is even  (edge case)")
    void isEvenOfZero() {
        assertTrue(Computations.isEven(0));
    }

    @Test
    @DisplayName("isEven: positive even numbers return true")
    void isEvenPositives() {
        assertTrue(Computations.isEven(2));
        assertTrue(Computations.isEven(100));
        assertTrue(Computations.isEven(1000000));
    }

    @Test
    @DisplayName("isEven: negative even numbers return true")
    void isEvenNegatives() {
        assertTrue(Computations.isEven(-2));
        assertTrue(Computations.isEven(-100));
    }

    @Test
    @DisplayName("isEven: odd numbers return false")
    void isEvenOddInputsReturnFalse() {
        assertFalse(Computations.isEven(1));
        assertFalse(Computations.isEven(3));
        assertFalse(Computations.isEven(-7));
        assertFalse(Computations.isEven(99));
    }

    // ============================================================
    // isOdd(int number)
    // ============================================================

    @Test
    @DisplayName("isOdd: 0 is NOT odd  (edge case)")
    void isOddOfZero() {
        assertFalse(Computations.isOdd(0));
    }

    @Test
    @DisplayName("isOdd: positive odd numbers return true")
    void isOddPositives() {
        assertTrue(Computations.isOdd(1));
        assertTrue(Computations.isOdd(7));
        assertTrue(Computations.isOdd(999));
    }

    @Test
    @DisplayName("isOdd: negative odd numbers return true")
    void isOddNegatives() {
        assertTrue(Computations.isOdd(-1));
        assertTrue(Computations.isOdd(-99));
    }

    @Test
    @DisplayName("isEven and isOdd should always disagree")
    void isEvenAndIsOddAreOpposites() {
        for (int n = -10; n <= 10; n++) {
            assertNotEquals(Computations.isEven(n), Computations.isOdd(n),
                "isEven(" + n + ") and isOdd(" + n + ") should be opposite");
        }
    }

    // ============================================================
    // toCelsius(double fahrenheit)
    // ============================================================

    private static final double DELTA = 0.0001;

    @Test
    @DisplayName("toCelsius: freezing point  32F -> 0C  (edge case)")
    void toCelsiusFreezing() {
        assertEquals(0.0, Computations.toCelsius(32.0), DELTA);
    }

    @Test
    @DisplayName("toCelsius: boiling point  212F -> 100C")
    void toCelsiusBoiling() {
        assertEquals(100.0, Computations.toCelsius(212.0), DELTA);
    }

    @Test
    @DisplayName("toCelsius: -40F -> -40C  (the famous crossover point)")
    void toCelsiusCrossover() {
        assertEquals(-40.0, Computations.toCelsius(-40.0), DELTA);
    }

    @Test
    @DisplayName("toCelsius: body temperature  98.6F -> 37C")
    void toCelsiusBodyTemp() {
        assertEquals(37.0, Computations.toCelsius(98.6), DELTA);
    }

    // ============================================================
    // toFahrenheit(double celsius)
    // ============================================================

    @Test
    @DisplayName("toFahrenheit: 0C -> 32F  (edge case)")
    void toFahrenheitFreezing() {
        assertEquals(32.0, Computations.toFahrenheit(0.0), DELTA);
    }

    @Test
    @DisplayName("toFahrenheit: 100C -> 212F")
    void toFahrenheitBoiling() {
        assertEquals(212.0, Computations.toFahrenheit(100.0), DELTA);
    }

    @Test
    @DisplayName("toFahrenheit: -40C -> -40F  (crossover)")
    void toFahrenheitCrossover() {
        assertEquals(-40.0, Computations.toFahrenheit(-40.0), DELTA);
    }

    @Test
    @DisplayName("toFahrenheit and toCelsius should be inverses")
    void temperatureConversionsAreInverses() {
        double[] tempsF = { -100.0, -40.0, 0.0, 32.0, 72.5, 100.0, 212.0, 451.0 };
        for (double f : tempsF) {
            double roundTrip = Computations.toFahrenheit(Computations.toCelsius(f));
            assertEquals(f, roundTrip, DELTA,
                "Round-tripping " + f + "F through Celsius and back should return the original");
        }
    }
}
