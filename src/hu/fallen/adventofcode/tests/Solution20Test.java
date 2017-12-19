package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution20;

public class Solution20Test {
	
	@Test
	public void test() {
		assertEquals(0, Solution20.calculate(new ArrayList<String>()));
		assertEquals(0, Solution20.calculate2(new ArrayList<String>()));
	}
	
}
