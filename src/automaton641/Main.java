package automaton641;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {
	public static int iterationTime = 64;
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Automaton");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Binary binary = new Binary(64, 64, 16, 16, 4, 2, 4);
        Canvas mainComponent = new Canvas(binary, 12);
        jFrame.add(mainComponent);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        while (true) {
        	mainComponent.drawAutomaton();
        	binary.iterate();
        }
    }
}
