package automaton641;

import java.util.Random;

public class GrowGraph {
	public DirectionGraph first;
	public DirectionGraph last;
	public Random random;
	public GrowGraph(Random random) {
		this.random = random;
	}
	public void addDirectionGraph(DirectionGraph directionGraph) {
		if (first == null) {
			directionGraph.next = directionGraph;
			directionGraph.previous = directionGraph;
			first = directionGraph;
			last = directionGraph;
		} else if (first == last) {
			last = directionGraph;
			first.next = last;
			first.previous = last;
			last.next = first;
			last.previous = first;
		} else {
			last.next = directionGraph;
			first.previous = directionGraph;
			directionGraph.previous = last;
			directionGraph.next = first;
			last = directionGraph;
		}
	}
	public void randomize(int size, int innerSize, int iterations) {
		for (int i = 0; i < size; i++) {
			this.addDirectionGraph(new DirectionGraph(random, innerSize, iterations));
		}
	}
}

