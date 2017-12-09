package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution6;

public class Solution6Test {

    @Test
    public void test() {
        int[] input = {0, 2, 7, 0};
        assertEquals(5,  Solution6.calculate(input));
    }

}
