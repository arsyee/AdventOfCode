package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Solution5 {

    public static void printSolution() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input5.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(Integer.toString(calculate(input)));
        System.out.println(Integer.toString(calculate2(input)));
    }
    
    public static int calculate(List<String> input) {
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
                ++pointers[instruction];
                instruction = newInstruction;
            }
        } catch (Exception e) {}
        return count;
    }

    public static int calculate2(List<String> input) {
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
                pointers[instruction] += pointers[instruction] >= 3 ? -1 : 1;
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
