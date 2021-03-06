package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import hu.fallen.adventofcode.helper.Pair.IntPair;

public class Solution08 {

    public static void printSolution() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input08.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        List<Instruction> instructions = extractInstructions(input);
        System.out.println(calculate(instructions));
    }

    public static IntPair calculate(List<Instruction> instructions) {
        Register register = new Register();
        System.out.println("There are "+instructions.size()+" instructions");
        for (Instruction instruction : instructions) {
            if (register.evaluate(instruction.operator, instruction.operand1, instruction.operand2)) {
                register.increment(instruction.register, instruction.sign*register.fetch(instruction.value));
            }
        }
        return new IntPair(register.max(), register.alltimeMax());
    }

    private static List<Instruction> extractInstructions(ArrayList<String> input) {
        ArrayList<Instruction> instructions = new ArrayList<Instruction>();
        for (String line : input) {
            instructions.add(new Instruction(line.split(" ")));
        }
        return instructions;
    }    
    
    static class Register extends HashMap<String, Integer> {
        private static final long serialVersionUID = 1L;
        int alltimeMax = 0;

        public boolean evaluate(String operator, Value operand1, Value operand2) {
            boolean result = false;
            switch(operator) {
                case ">":
                    result = fetch(operand1) > fetch(operand2);
                    break;
                case ">=":
                    result = fetch(operand1) >= fetch(operand2);
                    break;
                case "<":
                    result = fetch(operand1) < fetch(operand2);
                    break;
                case "<=":
                    result = fetch(operand1) <= fetch(operand2);
                    break;
                case "==":
                    result = fetch(operand1) == fetch(operand2);
                    break;
                case "!=":
                    result = fetch(operand1) != fetch(operand2);
                    break;
                default:
                    System.out.println("missing handler: "+operator);
            }
            System.out.println("evalute "+operand1 + operator + operand2 + " = " + result);
            return result;
        }
        
        public int alltimeMax() {
            return alltimeMax;
        }

        public int max() {
            return Collections.max(values()).intValue();
        }

        public void increment(String register, int increment) {
            put(register, get(register)+increment);
        }

        private int fetch(Value v) {
            if (v.register == null) {
                return v.intValue;
            }
            return get(v.register);
        }
        
        @Override
        public Integer put(String key, Integer value) {
            if (value.intValue() > alltimeMax) {
                alltimeMax = value.intValue();
            }
            return super.put(key, value);
        }
        
        @Override
        public Integer get(Object key) {
            if (!containsKey(key)) {
                put((String) key, new Integer(0));
            }
            return super.get(key);
        }
    }
    
    static class Instruction {
        public String register;
        public int sign;
        public Value value;
        public Value operand1;
        public String operator;
        public Value operand2;
        
        public Instruction(String[] tokens) {
            register = tokens[0];
            sign = tokens[1].equals("inc") ? 1 : -1;
            value = new Value(tokens[2]);
            operand1 = new Value(tokens[4]);
            operator = tokens[5];
            operand2 = new Value(tokens[6]);
        }
        
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(register).append(" ");
            builder.append(sign == 1 ? "inc" : "dec").append(" ");
            builder.append(value).append(" if ");
            builder.append(operand1).append(" ");
            builder.append(operator).append(" ");
            builder.append(operand2);
            return builder.toString();
        }
    }
    
    static class Value {
        public Integer intValue = 0;
        public String register = null;
        
        public Value(String string) {
            try {
                intValue = Integer.parseInt(string);
            } catch (NumberFormatException e) {
                register = string;
            }
        }
        
        @Override
        public String toString() {
            return register == null ? intValue.toString() : register;
        }
    }
    
}
