package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution09;

public class Solution09Test {
    
    @Test
    public void test() {
        assertEquals(1, Solution09.calculate("{}"));
        assertEquals(6, Solution09.calculate("{{{}}}"));
        assertEquals(5, Solution09.calculate("{{},{}}"));
        assertEquals(16, Solution09.calculate("{{{},{},{{}}}}"));
        assertEquals(1, Solution09.calculate("{<a>,<a>,<a>,<a>}"));
        assertEquals(9, Solution09.calculate("{{<ab>},{<ab>},{<ab>},{<ab>}}"));
        assertEquals(9, Solution09.calculate("{{<!!>},{<!!>},{<!!>},{<!!>}}"));
        assertEquals(3, Solution09.calculate("{{<a!>},{<a!>},{<a!>},{<ab>}}"));
        assertEquals(1, Solution09.calculate("{}"));
    }

}
