package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution14;

public class Solution14Test {

	@Test
	public void testCalculate() {
		assertEquals(8108, Solution14.calculate("flqrgnkx"));
		assertEquals(1242, Solution14.calculate2("flqrgnkx"));
	}
	
    @Test
    public void testCalculateRow() {
        assertEquals("d4", Solution14.calculateRow("flqrgnkx", 0).substring(0, 2));
        assertEquals("55", Solution14.calculateRow("flqrgnkx", 1).substring(0, 2));
        assertEquals("0a", Solution14.calculateRow("flqrgnkx", 2).substring(0, 2));
        assertEquals("ad", Solution14.calculateRow("flqrgnkx", 3).substring(0, 2));
        assertEquals("68", Solution14.calculateRow("flqrgnkx", 4).substring(0, 2));
        assertEquals("c9", Solution14.calculateRow("flqrgnkx", 5).substring(0, 2));
        assertEquals("44", Solution14.calculateRow("flqrgnkx", 6).substring(0, 2));
        assertEquals("d6", Solution14.calculateRow("flqrgnkx", 7).substring(0, 2));
    }

}
