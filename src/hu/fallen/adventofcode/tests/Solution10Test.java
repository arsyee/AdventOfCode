package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution10;

public class Solution10Test {
    
    @Test
    public void test() {
        assertEquals(12, Solution10.calculate(5, 3, 4, 1, 5));
        assertEquals(4480, Solution10.calculate(256, 63,144,180,149,1,255,167,84,125,65,188,0,2,254,229,24));
    }

}
