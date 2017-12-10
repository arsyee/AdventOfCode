package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

public class Solution09 {

    public static void printSolution() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input09.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(calculate(input.get(0)));
    }

    public static int calculate(String input) {
        int total = 0;
        int garbageCount = 0;
        int depth = 0;
        boolean garbage = false;
        boolean ignore = false;
        System.out.println(input);
        for (char c : input.toCharArray()) {
            if (garbage) {
                if (ignore) {
                    System.out.print(" ");
                    ignore = false;
                } else if (c == '>') {
                    System.out.print(">");
                    garbage = false;
                } else if (c == '!') {
                    System.out.print(" ");
                    ignore = true;
                } else {
                    garbageCount++;
                    System.out.print(".");
                }
            } else if (c == '<') {
                System.out.print("<");
                garbage = true;
            } else if (c == '{') {
                System.out.print("{");
                depth++;
                total += depth;
            } else if (c == '}') {
                System.out.print("}");
                depth--;
            } else {
                System.out.print(" ");
            }
        }
        System.out.println(total);
        System.out.println(garbageCount);
        return total;
    }
   
}
