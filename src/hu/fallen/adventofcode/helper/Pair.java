package hu.fallen.adventofcode.helper;

import java.util.AbstractMap.SimpleEntry;

public class Pair<K, V> extends SimpleEntry<K, V> {

    private static final long serialVersionUID = 1L;

    public Pair(K arg0, V arg1) {
        super(arg0, arg1);
    }

    @Override
    public String toString() {
        return new StringBuilder().append(super.getKey().toString()).append(", ").append(super.getValue()).toString();
    }
    
    @Override
    public boolean equals(Object o) {
        return (o instanceof Pair) && 
                super.getKey().equals(((Pair<?, ?>)o).getKey()) &&
                super.getValue().equals(((Pair<?, ?>)o).getValue());
    }
    
    public Object get(int i) {
        switch (i) {
            case 0:
                return super.getKey();
            case 1:
                return super.getValue();
            default:
                throw new IndexOutOfBoundsException();
        }
    }
    
    public static class IntPair extends Pair<Integer, Integer> {
        private static final long serialVersionUID = 1L;

        public IntPair(int arg0, int arg1) {
            super(arg0, arg1);
        }

    }
}
