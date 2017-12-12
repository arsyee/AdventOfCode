package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution11;

public class Solution11Test {
    
    @Test
    public void testCalculate() {
        assertEquals(3, Solution11.calculate("ne,ne,ne"));
        assertEquals(0, Solution11.calculate("ne,ne,sw,sw"));
        assertEquals(2, Solution11.calculate("ne,ne,s,s"));
        assertEquals(3, Solution11.calculate("se,sw,se,sw,sw"));
    }

}
