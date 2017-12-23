package hu.fallen.adventofcode.helper.solution18;

import java.util.LinkedList;

public class MessageQueue extends LinkedList<Long> {
	private static final long serialVersionUID = 1L;
	public long counter = 0;
	@Override
	public boolean add(Long e) {
		counter++;
		return super.add(e);
	}
}