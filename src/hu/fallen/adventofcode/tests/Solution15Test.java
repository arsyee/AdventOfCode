package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution15;

public class Solution15Test {
	
	Solution15.Generator A;
	Solution15.Generator B;
	
	@Before
	public void setup() {
		A = new Solution15.Generator(Solution15.FACTOR_A, 65, Solution15.CRITERIA_A);
		B = new Solution15.Generator(Solution15.FACTOR_B, 8921, Solution15.CRITERIA_B);		
	}
	
	@Test
	public void test() {
		assertEquals(588, Solution15.calculate(65, 8921));
		assertEquals(309, Solution15.calculate2(65, 8921));
	}
	
	@Test
	public void testJudge() {
		assertEquals(588, Solution15.judge(A, B, 40l*1000l*1000l, a -> a.getNext()));
	}
	
	@Test
	public void testJudgeValid() {
		assertEquals(309, Solution15.judge(A, B,  5l*1000l*1000l, a -> a.getNextValid()));
	}
	
	@Test
	public void testGeneratorA() {
		assertEquals(1092455, A.getNext());
		assertEquals(1181022009, A.getNext());
		assertEquals(245556042, A.getNext());
		assertEquals(1744312007, A.getNext());
		assertEquals(1352636452, A.getNext());
	}
	
	@Test
	public void testGeneratorB() {
		assertEquals(430625591, B.getNext());
		assertEquals(1233683848, B.getNext());
		assertEquals(1431495498, B.getNext());
		assertEquals(137874439, B.getNext());
		assertEquals(285222916, B.getNext());
	}
	
	@Test
	public void testGeneratorAValid() {
		assertEquals(1352636452, A.getNextValid());
		assertEquals(1992081072, A.getNextValid());
		assertEquals(530830436, A.getNextValid());
		assertEquals(1980017072, A.getNextValid());
		assertEquals(740335192, A.getNextValid());
	}
	
	@Test
	public void testGeneratorBValid() {
		assertEquals(1233683848, B.getNextValid());
		assertEquals(862516352, B.getNextValid());
		assertEquals(1159784568, B.getNextValid());
		assertEquals(1616057672, B.getNextValid());
		assertEquals(412269392, B.getNextValid());
	}
}
