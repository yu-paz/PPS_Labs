import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

/**
 * Part 3: A JUnit test that PASSES only if strOne and strTwo contain
 * the same characters. "Contain the same characters" can mean two things;
 * the most common interpretation in testing is "they are anagrams of each
 * other" (same characters, possibly in different order, same multiplicities).
 *
 * Approach: sort the chars of each string and compare the resulting arrays.
 * That way "listen" and "silent" are considered to contain the same chars.
 *
 * If your assignment instead means "exact same string", you would just use
 *   assertEquals(strOne, strTwo);
 * I included a second test below showing that variant as well.
 */
class StringSameCharactersTest {

    @Test
    void stringsShouldContainSameCharacters_anagram() {
        String strOne = "listen";
        String strTwo = "silent";

        char[] a = strOne.toCharArray();
        char[] b = strTwo.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);

        assertArrayEquals(a, b,
            "Expected the two strings to contain the same characters");
    }

    @Test
    void stringsShouldContainSameCharacters_exactMatch() {
        // Stricter interpretation: same characters in the same order
        String strOne = "hello";
        String strTwo = "hello";

        assertEquals(strOne, strTwo,
            "Expected the two strings to be character-for-character equal");
    }
}
