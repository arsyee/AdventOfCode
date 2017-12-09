package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution1;

public class Solution1Test {

    @Test
    public void test() {
        assertEquals(3, Solution1.calculate("1122"));
        assertEquals(4, Solution1.calculate("1111"));
        assertEquals(0, Solution1.calculate("1234"));
        assertEquals(9, Solution1.calculate("91212129"));
    }

}
