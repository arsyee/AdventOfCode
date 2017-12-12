package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution05;
import hu.fallen.adventofcode.helper.Pair.IntPair;

public class Solution05Test {

    @Test
    public void test() {
        ArrayList<String> input = new ArrayList<String>(Arrays.asList("0", "3", "0", "1", "-3"));
        assertEquals(new IntPair(5, 10), Solution05.calculate(input));
    }

}
