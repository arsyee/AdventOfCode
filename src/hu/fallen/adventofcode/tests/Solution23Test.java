package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution23;

public class Solution23Test {
	
	@Test
	public void test() {
		assertEquals(0, Solution23.calculate(new ArrayList<String>()));
		assertEquals(0, Solution23.calculate2(new ArrayList<String>()));
	}
	
}
