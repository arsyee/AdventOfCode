package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import hu.fallen.adventofcode.helper.Pair;
import hu.fallen.adventofcode.solutions.Solution12;

public class Solution12Test {
    
    @Test
    public void testCalculate() {
        List<String> input = Arrays.asList(("0 <-> 2\n"+
                "1 <-> 1\n"+
                "2 <-> 0, 3, 4\n"+
                "3 <-> 2, 4\n"+
                "4 <-> 2, 3, 6\n"+
                "5 <-> 6\n"+
                "6 <-> 4, 5").split("\n"));
        assertEquals(new Pair<Integer, Integer>(6, 2), Solution12.calculate(input));
    }

}
