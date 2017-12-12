package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution11 {

    public static void printSolution() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input11.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(calculate(input.get(0)));
        System.out.println(calculate2(input.get(0)));
    }
    
    public static int calculate(String input) {
        String[] steps = input.split(",");
        Hex position = new Hex(0, 0);
        for (String step : steps) {
            int dir = convertDirection(step);
            position = position.neighbor(dir);
        }
        return new Hex(0, 0).distance(position);
    }

    public static int calculate2(String input) {
        String[] steps = input.split(",");
        final Hex zero = new Hex(0, 0);
        Hex position = new Hex(0, 0);
        int max = 0;
        for (String step : steps) {
            int dir = convertDirection(step);
            position = position.neighbor(dir);
            int distance = zero.distance(position);
            if (distance > max) max = distance;
        }
        return max;
    }
    
    // https://www.redblobgames.com/grids/hexagons/
    // using: flat-topped system with odd-q offset coordinates
    
    private static String[] directions = {"se", "ne", "n", "nw", "sw", "s"};
    
    private static int convertDirection(String step) {
        return Arrays.asList(directions).indexOf(step);
    }
   
    static class Hex {
        int x = 0;
        int y = 0;
        
        Hex(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int distance(Hex position) {
            return new Cube(this).distance(new Cube(position));
        }

        public Hex(Hex hex) {
            x = hex.x;
            y = hex.y;
        }

        public Hex neighbor(int direction) {
            Hex dir = oddq_directions[Math.abs(this.x) % 2][direction];
            return new Hex(this.x+dir.x, this.y+dir.y);
        }
        
        private static Hex[][] oddq_directions = {{new Hex(+1, +0), new Hex(+1, -1), new Hex(+0, -1),
            new Hex(-1, -1), new Hex(-1, +0), new Hex(+0, +1)},
           {new Hex(+1, +1), new Hex(+1, +0), new Hex(+0, -1),
            new Hex(-1, +0), new Hex(-1, +1), new Hex(+0, +1)}};

    }

    static class Cube {
        int x = 0;
        int y = 0;
        int z = 0;
        
        Cube(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        
        public int distance(Cube cube) {
            return (Math.abs(this.x - cube.x) + Math.abs(this.y - cube.y) + Math.abs(this.z - cube.z)) / 2;
        }

        public Cube(Hex hex) {
            this.x = hex.x;
            this.z = hex.y - (hex.x - (Math.abs(hex.x) % 2)) / 2;
            this.y = -x - z;
        }
    }

}
