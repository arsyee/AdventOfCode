package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution3;

public class Solution3Test {

    @Test
    public void test() {
        assertEquals(0, Solution3.calculate(1));
        
        assertEquals(2, Solution3.calculate(3));
        assertEquals(2, Solution3.calculate(5));
        assertEquals(2, Solution3.calculate(7));
        assertEquals(2, Solution3.calculate(9));

        assertEquals(4, Solution3.calculate(13));
        assertEquals(4, Solution3.calculate(17));
        assertEquals(4, Solution3.calculate(21));
        assertEquals(4, Solution3.calculate(25));

        assertEquals(3, Solution3.calculate(12));
        assertEquals(2, Solution3.calculate(23));
        assertEquals(31, Solution3.calculate(1024));
        
        assertEquals(438, Solution3.calculate(265149));
    }

    @Test
    public void testLayer() {
        // max values in layers are squares of odd numbers
        assertEquals(1, Solution3.layer(1));
        assertEquals(2, Solution3.layer(9));
        assertEquals(3, Solution3.layer(25));
        assertEquals(4, Solution3.layer(49));

        // min values in layers: 
        assertEquals(2, Solution3.layer(2));
        assertEquals(3, Solution3.layer(10));
        assertEquals(4, Solution3.layer(26));
        assertEquals(5, Solution3.layer(50));
        
        // other tests:
        assertEquals(2, Solution3.layer(5));        
    }
    
    @Test
    public void testLayerSize() {
        assertEquals(0, Solution3.layerSize(0));
        assertEquals(1, Solution3.layerSize(1));
        assertEquals(8, Solution3.layerSize(2));
        assertEquals(16, Solution3.layerSize(3));
        assertEquals(24, Solution3.layerSize(4));
    }
}
