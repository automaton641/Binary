package automaton641;

public class Walker {
	public Binary binary;
	public Position position;
	public DirectionGraph directionGraph;
	public DirectionNode directionNode;
	public int iteration = 0;
	public int innerIteration = 0;
	public Walker(Binary binary, DirectionGraph directionGraph, int row, int column) {
		this.position = new Position(row, column);
		this.directionGraph = directionGraph;
		this.directionNode = directionGraph.first;
		this.binary = binary;
	}
	public void moveDown() {
		binary.moveDown(position);
	}
	public void moveDownOffset(int offset) {
		binary.moveDownOffset(position, offset);
	}
	
	public void moveRight() {
		binary.moveRight(position);
	}
	public void moveRightOffset(int offset) {
		binary.moveRightOffset(position, offset);
	}
	
	public void moveUp() {
		binary.moveUp(position);

	}
	public void moveUpOffset(int offset) {
		binary.moveUpOffset(position, offset);
	}
	
	public void moveLeft() {
		binary.moveLeft(position);

	}
	public void moveLeftOffset(int offset) {
		binary.moveLeftOffset(position, offset);
	}
	
	public void move(boolean direction, int offset) {
		switch (directionNode.direction) {
		case DOWN:
			moveDown();
			break;
		case RIGHT:
			moveRight();
			break;
		case UP:
			moveUp();
			break;
		case LEFT:
			moveLeft();
			break;
		default:
			System.out.println("ERROR: Invalid direction...");
			System.exit(0);
		}
		directionNode = directionNode.next;
		innerIteration++;
		if (innerIteration == directionGraph.size) {
			innerIteration = 0;
			//System.out.println("MATCH");
			iteration++;
			switch (directionNode.direction) {
			case DOWN:
				moveDownOffset(offset);
				break;
			case RIGHT:
				moveRightOffset(offset);
				break;
			case UP:
				moveUpOffset(offset);
				break;
			case LEFT:
				moveLeftOffset(offset);
				break;
			default:
				System.out.println("ERROR: Invalid direction...");
				System.exit(0);
			}
		}
		if (iteration == directionGraph.iterations) {
			iteration = 0;
			innerIteration = 0;
			//System.out.println("BIG-MATCH!!!");
			directionGraph = directionGraph.next;
			directionNode = directionGraph.first;
		}
	}
}
