package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;

import hu.fallen.adventofcode.helper.Pair.IntPair;

public class Solution24 {

    public static void printSolution() {
    	String mark = new Object() {}.getClass().getEnclosingClass().getSimpleName().replace("Solution", "");
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input"+mark+".txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Calculate 1");
        System.out.println(calculate(input));
        System.out.println("Calculate 1 (variant)");
        System.out.println(calculate1(input));
        System.out.println("Calculate 2");
        System.out.println(calculate2(input));
    }

    
	public static int calculate(ArrayList<String> input) {
	    ComponentSet components = createComponents(input);
		return strongestStrength(new ComponentSet(components), 0);
	}

	static final int DEBUGDEPTH = 3;
	static int inputSize = 0;
	static int printDepth = -1;
	static int recursionDepth = 0;
	static int maxRecursionDepth = 0;
	
	// 1607 is too low
    private static IntPair strongestLongest(ComponentSet components, int prev, int next) {
        printDepth += len(prev);
        recursionDepth++;
        int length = 0;
        int strength = 0;

        for (IntPair item : components) {
            int a = (Integer) item.get(0);
            int b = (Integer) item.get(1);
            if (b == next) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (a == next) {
                debug(a+"/"+b);
                ComponentSet removed = new ComponentSet(components);
                removed.remove(item);
                if (removed.size() != inputSize - recursionDepth) throw new RuntimeException();
                IntPair ls = strongestLongest(removed, a, b);
                int subLength = 1 + (Integer) ls.get(0);
                int subStrength = a + b + (Integer) ls.get(1);
                if (subLength > length || (subLength == length && subStrength > strength)) {
                    length = subLength;
                    strength = subStrength;
                }
            }
        }
        recursionDepth--;
        printDepth -= len(prev);
        return new IntPair(length, strength);
    }

    private static void debug(String s) {
        if (recursionDepth == maxRecursionDepth) {
            // System.out.println("deep we are again: " + maxRecursionDepth);
        }
        if (recursionDepth > maxRecursionDepth) {
            maxRecursionDepth = recursionDepth;
            // System.out.println("there is always deeper: " + maxRecursionDepth);
        }
        if (recursionDepth > DEBUGDEPTH) return;
        for (int i = 0; i < printDepth + 1; ++i) {
            System.out.print(" ");
        }
        System.out.println(s);
    }
    
    private static int len(int i) {
        int length = 2;
        while ((i /= 10) > 0) ++length;
        return length;
    }
    
    // 1695 is the solution
    private static int strongestStrength(ComponentSet components, int next) {
        int max = 0;
        for (IntPair item : components) {
            int a = (Integer)item.get(0);
            int b = (Integer)item.get(1);
            if (b == next) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (a == next) {
                ComponentSet removed = new ComponentSet(components);
                removed.remove(item);
                int sub = a + b + strongestStrength(removed, b);
                if (sub > max) max = sub;
            }
        }
        return max;
    }

    private static ComponentSet createComponents(ArrayList<String> input) {
        ComponentSet components = new ComponentSet();
        for (String line : input) {
            String[] tokens = line.split("/");
            components.add(new IntPair(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
        }
        return components;
    }

    static class ComponentSet extends HashSet<IntPair> {
        private static final long serialVersionUID = 1L;
        ComponentSet() { super(); }
        ComponentSet(ComponentSet original) {
            super();
            for (IntPair value : original) {
                this.add(new IntPair((Integer)value.get(0), (Integer)value.get(1)));
            }
        }
        @Override
        public boolean remove(Object o) {
            IntPair toBeRemoved = (IntPair) o;
            for (IntPair item : this) {
                if (((Integer)item.get(0)).equals((Integer) toBeRemoved.get(0)) &&
                    ((Integer)item.get(1)).equals((Integer) toBeRemoved.get(1))) {
                    return super.remove(item);
                }
            }
            throw new RuntimeException();
        }
    }
    
	public static IntPair calculate2(ArrayList<String> input) {
        inputSize = input.size();
        ComponentSet components = createComponents(input);
        if (components.size() != inputSize) throw new RuntimeException();
        return strongestLongest(new ComponentSet(components), 0, 0);
	}	

	// calculate 1 variant is based on calculate 2
	
    public static IntPair calculate1(ArrayList<String> input) {
        inputSize = input.size();
        ComponentSet components = createComponents(input);
        if (components.size() != inputSize) throw new RuntimeException();
        return strongestLongest1(new ComponentSet(components), 0, 0);
    }   
    
    // 1607 is too low
    private static IntPair strongestLongest1(ComponentSet components, int prev, int next) {
        printDepth += len(prev);
        recursionDepth++;
        int length = 0;
        int strength = 0;

        for (IntPair item : components) {
            int a = (Integer) item.get(0);
            int b = (Integer) item.get(1);
            if (b == next) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (a == next) {
                debug(a+"/"+b);
                ComponentSet removed = new ComponentSet(components);
                removed.remove(item);
                if (removed.size() != inputSize - recursionDepth) throw new RuntimeException();
                IntPair ls = strongestLongest1(removed, a, b);
                int subLength = 1 + (Integer) ls.get(0);
                int subStrength = a + b + (Integer) ls.get(1);
                if (subStrength > strength) {
                    length = subLength;
                    strength = subStrength;
                }
            }
        }
        recursionDepth--;
        printDepth -= len(prev);
        return new IntPair(length, strength);
    }
}
