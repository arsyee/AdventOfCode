package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution01;

public class Solution01Test {

    @Test
    public void test() {
        assertEquals(3, Solution01.calculate("1122"));
        assertEquals(4, Solution01.calculate("1111"));
        assertEquals(0, Solution01.calculate("1234"));
        assertEquals(9, Solution01.calculate("91212129"));
    }

}
