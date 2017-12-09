package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution9;

public class Solution9Test {
    
    @Test
    public void test() {
        assertEquals(1, Solution9.calculate("{}"));
        assertEquals(6, Solution9.calculate("{{{}}}"));
        assertEquals(5, Solution9.calculate("{{},{}}"));
        assertEquals(16, Solution9.calculate("{{{},{},{{}}}}"));
        assertEquals(1, Solution9.calculate("{<a>,<a>,<a>,<a>}"));
        assertEquals(9, Solution9.calculate("{{<ab>},{<ab>},{<ab>},{<ab>}}"));
        assertEquals(9, Solution9.calculate("{{<!!>},{<!!>},{<!!>},{<!!>}}"));
        assertEquals(3, Solution9.calculate("{{<a!>},{<a!>},{<a!>},{<ab>}}"));
        assertEquals(1, Solution9.calculate("{}"));
    }

}
