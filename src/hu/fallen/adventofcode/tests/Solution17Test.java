package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution17;

public class Solution17Test {
	
	@Test
	public void test() {
		assertEquals(0, Solution17.calculate(new ArrayList<String>()));
		assertEquals(0, Solution17.calculate2(new ArrayList<String>()));
	}
	
}
