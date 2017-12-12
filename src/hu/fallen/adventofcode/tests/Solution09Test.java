package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution09;

public class Solution09Test {
    
    @Test
    public void test() {
        assertEquals(1, Solution09.calculate("{}").get(0));
        assertEquals(6, Solution09.calculate("{{{}}}").get(0));
        assertEquals(5, Solution09.calculate("{{},{}}").get(0));
        assertEquals(16, Solution09.calculate("{{{},{},{{}}}}").get(0));
        assertEquals(1, Solution09.calculate("{<a>,<a>,<a>,<a>}").get(0));
        assertEquals(9, Solution09.calculate("{{<ab>},{<ab>},{<ab>},{<ab>}}").get(0));
        assertEquals(9, Solution09.calculate("{{<!!>},{<!!>},{<!!>},{<!!>}}").get(0));
        assertEquals(3, Solution09.calculate("{{<a!>},{<a!>},{<a!>},{<ab>}}").get(0));
        assertEquals(1, Solution09.calculate("{}").get(0));
    }

}
