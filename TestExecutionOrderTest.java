import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Part 4: If three tests are in the same class and the first fails,
 * do the other two still run?
 *
 * Answer: YES. JUnit treats each @Test method independently. A failing
 * assertion fails ONLY that one test. JUnit then proceeds to run every
 * other @Test method in the class. Any setup done in @BeforeEach is
 * re-run for each test, so tests don't share state.
 *
 * (Note: WITHIN a single test, the first failed assertion stops THAT
 * test method - subsequent assertions in the same method are skipped.
 * But that's a different question than what this exercise asks.)
 *
 * Run this class and you'll see in the report:
 *   firstTest      : FAILED
 *   secondTest     : PASSED
 *   thirdTest      : PASSED
 */
class TestExecutionOrderTest {

    @Test
    void firstTest_intentionallyFails() {
        System.out.println(">>> firstTest is running");
        // This assertion fails on purpose
        assertEquals(1, 2, "First test fails on purpose to prove the others still run");
    }

    @Test
    void secondTest_shouldStillRun() {
        System.out.println(">>> secondTest is running (proves test #1 failing did NOT stop us)");
        assertEquals(2, 1 + 1);
    }

    @Test
    void thirdTest_shouldStillRun() {
        System.out.println(">>> thirdTest is running (proves test #1 failing did NOT stop us)");
        assertTrue("hello".startsWith("he"));
    }
}
