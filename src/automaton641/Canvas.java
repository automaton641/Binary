package automaton641;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Canvas extends JComponent {
	
	public int cellSize;
    public Binary binary;
    public BufferedImage bufferedImage;
    public Color[] colors;
    
    public int getImageWidth() {
        return bufferedImage.getWidth();
    }
    
    public int getImageHeight() {
        return bufferedImage.getHeight();
    }

    public void drawAutomaton() {
        for (int row = 0; row < binary.height; row++) {
            for (int column = 0; column < binary.width; column++) {
                drawCell(row, column);
            }
        }
        repaint();
    }

    public void drawCell(int row, int column) {
        Color color = Color.RED;
        boolean missing = true;
        /*
        for (Iterator<Walker> iterator = binary.walkers.iterator(); iterator.hasNext() && missing;) {
        	Walker walker = iterator.next();
        	if (walker.position.row == row && walker.position.column == column) {
            	color = Color.BLUE;
            	missing = false;
            }
		}
		*/
        if (missing) {
            boolean value = binary.cells[row][column].value;
        	if (value) {
    			color = Color.WHITE;
    		} else {
    			color = Color.BLACK;
    		}
        }
        for (int y = row*cellSize; y < row *cellSize+cellSize; y++) {
            for (int x = column*cellSize; x < column*cellSize+cellSize; x++) {
                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }
    }
    
    public Canvas(Binary binary, int cellSize) {
        this.binary = binary;
        this.cellSize = cellSize;
        bufferedImage = new BufferedImage(binary.width*cellSize, binary.height*cellSize, BufferedImage.TYPE_INT_ARGB);
        for (int row = 0; row < bufferedImage.getHeight(); row++) {
            for (int column = 0; column < bufferedImage.getWidth(); column++) {
                bufferedImage.setRGB(column, row, new Color(0.5f, 0.5f, 0.5f).getRGB());
            }
        }
        setPreferredSize(new Dimension(getImageWidth(), getImageHeight()));
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), this);
    }
}
