package automaton641;

import java.util.Random;

import automaton641.DirectionNode.Direction;

public class DirectionGraph {
	public DirectionNode first;
	public DirectionNode last;
	public DirectionGraph next;
	public DirectionGraph previous;
	public int iterations;
	public int size;
	public Random random;
	public DirectionGraph(Random random) {
		this.random = random;
	}
	public DirectionGraph(Random random, int size, int iterations) {
		this.random = random;
		this.randomize(size);
		this.size = size;
		this.iterations = iterations;
	}
	public void addDirection(Direction direction) {
		DirectionNode directionNode = new DirectionNode();
		directionNode.direction = direction;
		if (first == null) {
			directionNode.next = directionNode;
			directionNode.previous = directionNode;
			first = directionNode;
			last = directionNode;
		} else if (first == last) {
			last = directionNode;
			first.next = last;
			first.previous = last;
			last.next = first;
			last.previous = first;
		} else {
			last.next = directionNode;
			first.previous = directionNode;
			directionNode.previous = last;
			directionNode.next = first;
			last = directionNode;
		}
	}
	public void randomize(int size) {
		for (int i = 0; i < size; i++) {
			this.addDirection(DirectionNode.generateRandomDirection(random));
		}
	}
}
