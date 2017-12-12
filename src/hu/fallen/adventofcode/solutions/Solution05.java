package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import hu.fallen.adventofcode.helper.Pair.IntPair;

public class Solution05 {

    public static void printSolution() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input05.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(calculate(input));
    }
    
    public static IntPair calculate(List<String> input) {
        return new IntPair(calculate_f(input, a -> 1), calculate_f(input, a -> a >= 3 ? -1 : 1));
    }

    public static int calculate_f(List<String> input, Function<Integer, Integer> increment) {
        int count = 0;
        int[] pointers = new int[input.size()];
        for (int i = 0; i < input.size(); ++i) {
            pointers[i] = Integer.parseInt(input.get(i));
        }
        int instruction = 0;
        try {
            while (true) {
                if (input.size() < 100) print(pointers, instruction);
                int newInstruction = instruction+pointers[instruction];
                ++count;
                pointers[instruction] += increment.apply(pointers[instruction]);
                instruction = newInstruction;
            }
        } catch (Exception e) {}
        return count;
    }

    static void print(int[] pointers, int instruction) {
        for (int i = 0; i < pointers.length; ++i) {
            if (i == instruction) {
                System.out.print("("+pointers[i]+") ");
            } else {
                System.out.print(pointers[i]+" ");
            }
        }
        System.out.println();
    }
    
}
