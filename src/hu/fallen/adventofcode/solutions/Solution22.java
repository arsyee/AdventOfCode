package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

public class Solution22 {

    public static void printSolution() {
    	String mark = new Object() {}.getClass().getEnclosingClass().getSimpleName().replace("Solution", "");
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input"+mark+".txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(calculate(input, 10000));
        System.out.println(calculate2(input, 10000000));
    }

	public static int calculate(ArrayList<String> input, int numBursts) {
	    Cluster cluster = new Cluster(input);
        cluster.print("Original");
	    for (int i = 0; i < numBursts; ++i) {
    	    cluster.burst();
            // cluster.print("After step " + i);
	    }
		return cluster.getInfectionCount();
	}

    public static int calculate2(ArrayList<String> input, int numBursts) {
        Cluster cluster = new EvolvedCluster(input);
        cluster.print("Original");
        for (int i = 0; i < numBursts; ++i) {
            cluster.burst();
            // cluster.print("After step " + i);
        }
        cluster.print("Result");
        return cluster.getInfectionCount();
    }

	/*
	 *   U
	 *  L R
	 *   D
	 *   
	 */
	static enum Facing {
	    U, L, D, R;
	    private static Facing[] vals = values();
	    public Facing left() {
	        return vals[(this.ordinal() + 1) % vals.length];
	    }
        public Facing right() {
            return vals[(vals.length + this.ordinal() - 1) % vals.length];
        }
        public Facing reverse() {
            return vals[(this.ordinal() + 2) % vals.length];
        }
	};
	
	static class EvolvedCluster extends Cluster {
	    public EvolvedCluster(ArrayList<String> input) {
	        super(input);
	    }
	    
	    @Override
        protected void alter() {
            switch (grid[pos[0]][pos[1]]) {
            case '.':
                grid[pos[0]][pos[1]] = 'w';
                break;
            case 'w':
                grid[pos[0]][pos[1]] = '#';
                ++infectionCount;
                break;
            case '#':
                grid[pos[0]][pos[1]] = 'f';
                break;
            case 'f':
                grid[pos[0]][pos[1]] = '.';
                break;
            }
	    }
	    
	    @Override
	    protected void turn() {
            if (grid[pos[0]][pos[1]] == '#') {
                facing = facing.right();
            } else if (grid[pos[0]][pos[1]] == '.') {
                facing = facing.left();
            } else if (grid[pos[0]][pos[1]] == 'f') {
                facing = facing.reverse();
            }
	    }
	}
	
	static class Cluster {

	    protected char[][] grid;
	    private int size;
	    
	    int[] pos = new int[2];
	    Facing facing = Facing.U;
	    
	    int infectionCount = 0;
	    
        public Cluster(ArrayList<String> input) {
            size = input.size();
            grid = new char[size][size];
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    grid[i][j] = input.get(i).charAt(j);
                }
            }
            pos[0] = size / 2;
            pos[1] = size / 2;
        }
	    
        public void burst() {
            turn();
            alter();
            move();
        }
        
        protected void turn() {
            if (grid[pos[0]][pos[1]] == '#') {
                facing = facing.right();
            } else {
                facing = facing.left();
            }
        }
        
        protected void alter() {
            switch (grid[pos[0]][pos[1]]) {
            case '#':
                grid[pos[0]][pos[1]] = '.';
                break;
            case '.':
                grid[pos[0]][pos[1]] = '#';
                ++infectionCount;
            }
        }
        
        private void move() {
            boolean toBeResized = false;
            switch (facing) {
            case U:
                pos[0]--;
                if (pos[0] < 0) toBeResized = true;
                break;
            case L:
                pos[1]--;
                if (pos[1] < 0) toBeResized = true;
                break;
            case D:
                pos[0]++;
                if (pos[0] >= size) toBeResized = true;
                break;
            case R:
                pos[1]++;
                if (pos[1] >= size) toBeResized = true;
                break;
            }
            if (!toBeResized) return;
            int newSize = size + 2;
            char[][] newGrid = new char[newSize][newSize];
            for (int i = 0; i < newSize; ++i) {
                newGrid[i][0] = '.';
                newGrid[i][newSize-1] = '.';
                newGrid[0][i] = '.';
                newGrid[newSize-1][i] = '.';
            }
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    newGrid[i+1][j+1] = grid[i][j];
                }
            }
            size = newSize;
            grid = newGrid;
            pos[0]++;
            pos[1]++;
        }

        public void print(String string) {
            System.out.println(string);
            System.out.println(this);
        }

        public int getInfectionCount() {
            return infectionCount;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    if (i == pos[0] && j == pos[1]) {
                        sb.append(grid[i][j] == '#' ? facing.toString().toUpperCase() : facing.toString().toLowerCase());
                    } else {
                        sb.append(grid[i][j]);
                    }
                }
                sb.append("\n");
            }
            return sb.toString();
        }
	}
	
}
