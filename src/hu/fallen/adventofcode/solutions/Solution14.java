package hu.fallen.adventofcode.solutions;

import java.util.HashMap;
import java.util.HashSet;

public class Solution14 {

    static boolean debug = true;
    
    public static void printSolution() {
        System.out.println(Solution14.calculate("hfdlxzhv"));
        System.out.println(Solution14.calculate2("hfdlxzhv"));
    }

    public static int calculate(String string) {
    	int count = 0;
    	for (int i = 0; i < 128; ++i) {
    		String hash = calculateRow(string, i);
    		for (int j = 0; j < 128/4; ++j) {
    			count += bitCount(hash.substring(j, j+1));
    		}
    	}
    	return count;
    }

    public static int calculate2(String string) {
    	// create a map of zeros and ones
    	int[][] map = new int[128][128];
    	for (int i = 0; i < 128; ++i) {
    		String hash = calculateRow(string, i);
    		for (int j = 0; j < 128/4; ++j) {
    			int nibble = Integer.parseInt(hash.substring(j, j+1), 16);
    			for (int k = 0; k < 4; ++k) {
    				map[i][4*j+(3-k)] = nibble % 2;
    				nibble /= 2;
    			}
    		}
    	}
    	
    	// create a list of present neighbors for each cell (a cell is identified by the linearized index)
    	HashMap<Integer, HashSet<Integer>> regions = new HashMap<>();
    	for (int i = 0; i < map.length; ++i) {
    		if (i == 8) {
    			// Solution12.debug = true;
    			Solution12.print(regions);
    			Solution12.debug = false;
    			System.out.println(regions.size());
    		}
    		for (int j = 0; j < map[i].length; ++j) {
    			if (map[i][j] == 0) continue;
    			HashSet<Integer> set = new HashSet<Integer>();
    			for (int ii = -1; ii <= 1; ++ii) {
    				if (i + ii < 0 || i + ii >= map.length) continue; // ArrayIndexOutOfBounds
    				for (int jj = -1; jj <= 1; ++jj) {
    					if (j + jj < 0 || j +jj >= map[i+ii].length) continue;  // ArrayIndexOutOfBounds
    					if (ii * jj != 0) continue; // Diagonals not included
    					if (map[i+ii][j+jj] == 0) continue; // Empty area
    					int neighbour = (i+ii) * map.length + (j+jj);
    					set.add(neighbour);
    				}
    			}
    			int current = i * map.length + j;
    			regions.put(current, set);
    		}
    	}
    	
    	// reduce the regions
    	System.out.println("Size before reduction: "+regions.size());
		Solution12.reduceEquivalenceClasses(regions);
    	System.out.println("Size after reduction: "+regions.size());
    	return regions.size();
    }

    private static int bitCount(String c) {
    	int nibble = Integer.parseInt(c, 16);
    	int result = 0;
    	while (nibble > 0) {
    		result += nibble % 2;
    		nibble /= 2;
    	}
		return result;
	}

	public static String calculateRow(String string, int row) {
    	Solution10.debug = false;
    	return Solution10.knot(string+"-"+row);
    }
    
}
