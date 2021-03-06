package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution03;

public class Solution03Test {

    @Test
    public void test() {
        assertEquals(0, Solution03.calculate(1));
        
        assertEquals(2, Solution03.calculate(3));
        assertEquals(2, Solution03.calculate(5));
        assertEquals(2, Solution03.calculate(7));
        assertEquals(2, Solution03.calculate(9));

        assertEquals(4, Solution03.calculate(13));
        assertEquals(4, Solution03.calculate(17));
        assertEquals(4, Solution03.calculate(21));
        assertEquals(4, Solution03.calculate(25));

        assertEquals(3, Solution03.calculate(12));
        assertEquals(2, Solution03.calculate(23));
        assertEquals(31, Solution03.calculate(1024));
        
        assertEquals(438, Solution03.calculate(265149));
    }

    @Test
    public void testLayer() {
        // max values in layers are squares of odd numbers
        assertEquals(1, Solution03.layer(1));
        assertEquals(2, Solution03.layer(9));
        assertEquals(3, Solution03.layer(25));
        assertEquals(4, Solution03.layer(49));

        // min values in layers: 
        assertEquals(2, Solution03.layer(2));
        assertEquals(3, Solution03.layer(10));
        assertEquals(4, Solution03.layer(26));
        assertEquals(5, Solution03.layer(50));
        
        // other tests:
        assertEquals(2, Solution03.layer(5));        
    }
    
    @Test
    public void testLayerSize() {
        assertEquals(0, Solution03.layerSize(0));
        assertEquals(1, Solution03.layerSize(1));
        assertEquals(8, Solution03.layerSize(2));
        assertEquals(16, Solution03.layerSize(3));
        assertEquals(24, Solution03.layerSize(4));
    }
}
