package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution5;

public class Solution5Test {

    @Test
    public void test() {
        ArrayList<String> input = new ArrayList<String>(Arrays.asList("0", "3", "0", "1", "-3"));
        assertEquals(5, Solution5.calculate(input));
    }

    @Test
    public void test2() {
        ArrayList<String> input = new ArrayList<String>(Arrays.asList("0", "3", "0", "1", "-3"));
        assertEquals(10, Solution5.calculate2(input));
    }

}
