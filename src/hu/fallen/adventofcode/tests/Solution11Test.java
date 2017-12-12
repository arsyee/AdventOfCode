package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.helper.Pair.IntPair;
import hu.fallen.adventofcode.solutions.Solution11;

public class Solution11Test {
    
    @Test
    public void testCalculate() {
        assertEquals(3, Solution11.calculate("ne,ne,ne").get(0));
        assertEquals(0, Solution11.calculate("ne,ne,sw,sw").get(0));
        assertEquals(2, Solution11.calculate("ne,ne,s,s").get(0));
        assertEquals(3, Solution11.calculate("se,sw,se,sw,sw").get(0));
    }

    @Test
    public void testExercise() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input11.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        assertEquals(new IntPair(818, 1596), Solution11.calculate(input.get(0)));
    }
}
