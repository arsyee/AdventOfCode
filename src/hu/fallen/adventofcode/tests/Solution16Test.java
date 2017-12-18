package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution16;

public class Solution16Test {
	
	@Test
	public void test() {
		assertEquals("abcde", Solution16.calculate("", 5));
		assertEquals("cdeab", Solution16.calculate("s3", 5));
		assertEquals("eabcd", Solution16.calculate("s1", 5));
		assertEquals("eabdc", Solution16.calculate("s1,x3/4", 5));
		assertEquals("baedc", Solution16.calculate("s1,x3/4,pe/b", 5));
		assertEquals("baedc", Solution16.calculateRepeat("s1,x3/4,pe/b", 5, 1));
		assertEquals("ceadb", Solution16.calculateRepeat("s1,x3/4,pe/b", 5, 2));
		assertEquals("abcdefghijklmnop", Solution16.calculate(""));
	}
	
}
