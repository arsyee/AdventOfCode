package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution10;

public class Solution10Test {
    
    @Test
    public void test() {
        assertEquals(12, Solution10.calculate(5, 3, 4, 1, 5));
    }

}
