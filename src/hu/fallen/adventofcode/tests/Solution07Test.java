package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution07;

public class Solution07Test {
    
    @Test
    public void test() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "test07.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        assertEquals("tknk", Solution07.calculate(input));
        assertEquals(60, Solution07.calculate2(input));
    }

}
