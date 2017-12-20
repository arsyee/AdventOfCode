package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Solution20 {

    public static void printSolution() {
    	String mark = new Object() {}.getClass().getEnclosingClass().getSimpleName().replace("Solution", "");
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input"+mark+".txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(calculate(input));
        System.out.println(calculate2(input));
    }

	public static int calculate(ArrayList<String> input) {
	    StateList stateList  = new StateList(input);
        int minI = 0;
	    int minA = stateList.get(0).a.size();
	    for (int i = 1; i < stateList.size(); ++i) {
	        if (stateList.get(i).a.size() < minA) {
	            minI = i;
	            minA = stateList.get(i).a.size();
	        }
	    }
		return minI;
	}

	public static int calculate2(ArrayList<String> input) {
	    StateList stateList = new StateList(input);
	    do {
	        stateList.removeCollosions();
	        stateList.move();
	    } while (!stateList.divergent());
		return stateList.size();
	}

	static class StateList extends ArrayList<State> {
        private static final long serialVersionUID = 1L;

        StateList(ArrayList<String> input) {
	        for (String line : input) {
	            this.add(new State(line));
	        }
	    }

        int counter = 1000;
        
        public boolean divergent() {
            --counter;
            return counter == 0;
        }

        public void move() {
            for (State state : this) {
                state.move();
            }
        }

        public void removeCollosions() {
            List<State> toBeRemoved = new ArrayList<State>();
            for (State state1 : this) {
                // System.out.println(state1);
                for (State state2 : this) {
                    if (state1 != state2 && state1.p.equals(state2.p)) {
                        System.out.println("Match found!");
                        toBeRemoved.add(state1);
                        toBeRemoved.add(state2);
                    }
                }
            }
            for (State state : toBeRemoved) {
                this.remove(state);
                counter = 100;
            }
        }
	}
	
	static class State {
	    Coordinate p;
	    Coordinate v;
	    Coordinate a;
	    
        public State(String line) {
            String[] sections = line.split(", ");
            p = new Coordinate(sections[0]);
            v = new Coordinate(sections[1]);
            a = new Coordinate(sections[2]);
        }

        public State(State state) {
            p = new Coordinate(state.p);
            v = new Coordinate(state.v);
            a = new Coordinate(state.a);
        }

        int distance() {
	        return p.x+p.y+p.z;
	    }
	    
	    void move() {
	        v.x += a.x;
	        p.x += v.x;
	        
	        v.y += a.y;
	        p.y += a.y;
	        
	        v.z += a.z;
	        p.z += a.z;
	    }
	    
	    void move(int n) {
            p.x += (n * (n + 1)) / 2 * a.x + n * v.x;
	        v.x += n * a.x;
	        
            p.y += (n * (n + 1)) / 2 * a.y + n * v.y;
            v.y += n * a.y;
            
            p.z += (n * (n + 1)) / 2 * a.z + n * v.z;
            v.z += n * a.z;
	    }
	    
	    @Override
	    public String toString() {
	        return "("+p+" : "+v+" : "+a+")";
	    }
	}
	
	static class Coordinate {
        int x;
        int y;
        int z;
        
	    public Coordinate(String string) {
            String stripped = string.substring(3, string.length()-1);
            String[] tokens = stripped.split(",");
            x = Integer.parseInt(tokens[0].trim());
            y = Integer.parseInt(tokens[1].trim());
            z = Integer.parseInt(tokens[2].trim());
        }
	    
	    public int size() {
            return Math.abs(x) + Math.abs(y) + Math.abs(z);
        }

        public Coordinate(Coordinate v) {
            x = v.x;
            y = v.y;
            z = v.z;
        }

        @Override
        public boolean equals(Object arg0) {
            if (arg0 == null) return false;
            if (!(arg0 instanceof Coordinate)) return false;
            Coordinate other = (Coordinate) arg0;
            return x == other.x && y == other.y && z == other.z;
        }
        
        @Override
	    public String toString() {
	        return ""+x+","+y+","+z;
	    }
	}
	
    private static void print(List<Integer> buffer, int position) {
        for (int i = 0; i < buffer.size(); ++i) {
            System.out.print(i == position ? "(" : " ");
            System.out.print(buffer.get(i));
            System.out.print(i == position ? ")" : " ");
            System.out.print(" ");
        }
        System.out.println();
    }   
}
