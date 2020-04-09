package automaton641;

import java.util.Random;

public class Cell {
    public Cell(Random random) {
        this.random = random;
    }
    private Random random;
    public boolean value;
    public void randomize() {
        value = random.nextBoolean();
    }
}
