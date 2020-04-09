package automaton641;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import automaton641.DirectionNode.Direction;

public class Binary {
	public LinkedList<Walker> walkers;
	public int offset;
    public Random random;
    public Cell[][] cells;
    public Cell[][] backCells;
    public int width;
    public int height;
    public Binary(int width, int height, int steps, int innerSteps , int iterations, int offset, int splits) {
		this.offset = offset;
        this.width = width;
        this.height = height;
        random = new Random();
        initializeCells();
        GrowGraph growGraph = new GrowGraph(random);
        growGraph.randomize(steps, innerSteps, iterations);
        this.walkers = new LinkedList<Walker>();
        for (int i = 0; i < splits; i++) {
        	for (int j = 0; j < splits; j++) {
                this.walkers.add(new Walker(this, growGraph.first, height/splits*i, width/splits*j));
    		}
		}

    }
    public void initializeCells() {
        cells = new Cell[height][width];
        backCells = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                cells[row][column] = new Cell(random);
                backCells[row][column] = new Cell(random);
            }
        }
        /*
        cells[height/2][width/2].value = true;
        cells[height/2][width/2 - 1].value = true;
        cells[height/2 - 1][width/2].value = true;
        cells[height/2 - 1][width/2 - 1].value = true;
		*/
        fillBackCells();
    }
    
    public void fillBackCells() {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                backCells[row][column].value = cells[row][column].value;
            }
        }
    }
    
    public void moveDown(Position position) {
    	if (position.row == height - 1) {
    		position.row = 0;
        } else {
        	position.row++;
        }
    }
    
    public void moveRight(Position position) {
    	if (position.column == width - 1) {
    		position.column = 0;
        } else {
        	position.column++;
        }
    }
    
    public void moveUp(Position position) {
    	if (position.row == 0) {
    		position.row = height - 1;
        } else {
        	position.row--;
        }
    }
    
    public void moveLeft(Position position) {
    	if (position.column == 0) {
    		position.column = width - 1;
        } else {
        	position.column--;
        }
    }
    
    public boolean getLeftValue(int row, int column) {
        if (column == 0) {
            column = width - 1;
        } else {
            column--;
        }
        return backCells[row][column].value;
    }
    
    public boolean getUpValue(int row, int column) {
        if (row == 0) {
            row = height - 1;
        } else {
            row--;
        }
        return backCells[row][column].value;
    }
    
    public boolean getRightValue(int row, int column) {
        if (column == width - 1) {
            column = 0;
        } else {
            column++;
        }
        return backCells[row][column].value;
    }
    
    public boolean getDownValue(int row, int column) {
        if (row == height - 1) {
            row = 0;
        } else {
            row++;
        }
        return backCells[row][column].value;
    }
    
    public void iterate() {
    	for (Iterator<Walker> iterator = walkers.iterator(); iterator.hasNext();) {
			Walker walker = iterator.next();
			for (int i = 0; i < 1; i++) {
				boolean floor = cells[walker.position.row][walker.position.column].value;
		        //System.out.println(walker.position);
		        //System.out.println(floor);
		        //System.out.println(walker.directionNode);
		        cells[walker.position.row][walker.position.column].value = !floor;
		        walker.move(floor, offset);
			}
		}
        try {
			Thread.sleep(Main.iterationTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void print() {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                System.out.println("[" + row + ", " + column + "] = " + cells[row][column].value);
            }
        }
    }

	public void moveDownOffset(Position position, int offset) {
		position.row += offset;
		while (position.row >= height) {
			position.row -= height;
		}
	}

	public void moveRightOffset(Position position, int offset) {
		position.column += offset;
		while (position.column >= width) {
			position.column -= width;
		}		
	}

	public void moveUpOffset(Position position, int offset) {
		position.row -= offset;
		while (position.row < 0) {
			position.row += height;
		}		
	}

	public void moveLeftOffset(Position position, int offset) {
		position.column -= offset;
		while (position.column < 0) {
			position.column += width;
		}
	}
    
}
