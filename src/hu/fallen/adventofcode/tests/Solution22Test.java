package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution22;

public class Solution22Test {
	
	@Test
	public void test() {
		assertEquals(0, Solution22.calculate(new ArrayList<String>()));
		assertEquals(0, Solution22.calculate2(new ArrayList<String>()));
	}
	
}
