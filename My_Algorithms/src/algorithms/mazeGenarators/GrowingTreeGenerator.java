package algorithms.mazeGenarators;

import java.util.ArrayList;
import java.util.Random;
import strategy.choosecell.CellChoice;
import strategy.choosecell.RandomCell;
import algorithms.mazeGenarators.Direction;

/**
 * @author Tal Mishaan 203908652 & Guy Binyamin 200958098
 *
 */
public class GrowingTreeGenerator extends Maze3dAlgorithms {
	private Random rand = new Random();
	private Maze3d maze3d;
	private ArrayList<Position> move_order = new ArrayList<Position>();
	Position goalPosition;
	private CellChoice cell_choice = new RandomCell();
	
	/**
	 * This method gets a random position in the maze
	 * @return Position This returns the random position
	 */
	private Position chooseRandomPosition() {
		// Get random column, row and level to get a space position
		int column = rand.nextInt(maze3d.getColumns());
		while (column % 2 == 0)
			column = rand.nextInt(maze3d.getColumns());
		
		int row = rand.nextInt(maze3d.getRows());
		while (row % 2 == 0)
			row = rand.nextInt(maze3d.getRows());
		
		int level = rand.nextInt(maze3d.getLevels());
		while (level % 2 == 0)
			level = rand.nextInt(maze3d.getLevels());
		
		if(maze3d.getValue(level, row, column) == 0)
			return chooseRandomPosition(); // assure the space isn't set already
		
		return new Position(level, row, column);		
	}
		
	@Override
	public Maze3d generate(int levels, int rows, int cols) {
		maze3d = new Maze3d(levels*2+1, rows*2+1, cols*2+1); // To build walls
		
		for (int z = 0; z < maze3d.getLevels(); z++)
			for (int y = 0; y < maze3d.getRows(); y++)
				for (int x = 0; x < maze3d.getColumns(); x++)
					maze3d.setValue(z, y, x, 1); // Set wall to everything
		
		Position startPos = chooseRandomPosition(); // Random position in the maze 
//		System.out.println("Start:: "+startPos.toString());
//		System.out.println("Goal:: "+goalPosition.toString());
		
		maze3d.setStartPosition(startPos);
		
		goalPosition = chooseRandomPosition();
		maze3d.setGoalPosition(goalPosition);

		// Start!
		GrowingTree(startPos);
		
		return maze3d;
	}
	
	/**
	 * This method recursively gets position and break random nearby wall
	 * @param currPos The current position
	 */
	private void GrowingTree(Position currPos) {
		Position newPos;

		if (currPos == null){
			return; // End Growing tree
		}
		
		move_order.add(0, currPos); // Add current to stack
		
		// Get possible moves to go
		ArrayList<Direction> dirs = getPossibleDirections(currPos);
		
		// assure dirs is set
		if (dirs.size() == 0)
			dirs.add(Direction.NO_WAY);

		// Random direction
		int dir = rand.nextInt(dirs.size());
		
		// What is the direction randomed?
		switch (dirs.get(dir)) {
		case Right:
			maze3d.setFree(currPos.getLevel(), currPos.getRow(), currPos.getColm() + 1);
			maze3d.setFree(currPos.getLevel(), currPos.getRow(), currPos.getColm() + 2);
			newPos = new Position(currPos.getLevel(), currPos.getRow(), currPos.getColm() + 2);
		break;
		case Left:
			maze3d.setFree(currPos.getLevel(), currPos.getRow(), currPos.getColm() - 1);
			maze3d.setFree(currPos.getLevel(), currPos.getRow(), currPos.getColm() - 2);
			newPos = new Position(currPos.getLevel(), currPos.getRow(), currPos.getColm() - 2);
		break;
		case Forward:
			maze3d.setFree(currPos.getLevel(), currPos.getRow() + 1, currPos.getColm());
			maze3d.setFree(currPos.getLevel(), currPos.getRow() + 2, currPos.getColm());
			newPos = new Position(currPos.getLevel(), currPos.getRow() + 2, currPos.getColm());
		break;
		case Backward:
			maze3d.setFree(currPos.getLevel(), currPos.getRow() - 1, currPos.getColm());
			maze3d.setFree(currPos.getLevel(), currPos.getRow() - 2, currPos.getColm());
			newPos = new Position(currPos.getLevel(), currPos.getRow() - 2, currPos.getColm());
		break;
		case Up:
			maze3d.setFree(currPos.getLevel() + 1, currPos.getRow(), currPos.getColm());
			maze3d.setFree(currPos.getLevel() + 2, currPos.getRow(), currPos.getColm());
			newPos = new Position(currPos.getLevel() + 2, currPos.getRow(), currPos.getColm());
		break;
		case Down:
			maze3d.setFree(currPos.getLevel() - 1, currPos.getRow(), currPos.getColm());
			maze3d.setFree(currPos.getLevel() - 2, currPos.getRow(), currPos.getColm());
			newPos = new Position(currPos.getLevel() - 2, currPos.getRow(), currPos.getColm());
		break;
		default:
			
			// If the stack has at least 1 way back to go
			if(move_order.size() >= 2)
				newPos = cell_choice.getCell(move_order);
			else
				newPos = null; // END OF GrowingTree
			
			// Avoid exception if stck is empty
			if(move_order.size() >= 1)
				move_order.remove(0);
			
			if(move_order.size() >= 1)
				move_order.remove(0);
		break;
		}
		
		// Go next step
		GrowingTree(newPos);
	}
	
	/**
	 * This method returns a list of possible directions to go through from Position
	 * @param currPos This is the position
	 * @return ArrayList<Direction> This returns a possible Directions list
	 */
	private ArrayList<Direction> getPossibleDirections(Position currPos) {
		ArrayList<Direction> directions = new ArrayList<Direction>();
		
		if ((currPos.getColm() + 2 >= 0) && (currPos.getColm() + 2 < maze3d.getColumns()) && 
				maze3d.getValue(currPos.getLevel(), currPos.getRow(), currPos.getColm() + 2) == maze3d.WALL) {
			directions.add(Direction.Right);
		}
		
		if ((currPos.getColm() - 2 >= 0) && (currPos.getColm() - 2 < maze3d.getColumns()) && 
				maze3d.getValue(currPos.getLevel(), currPos.getRow(), currPos.getColm() - 2) == maze3d.WALL) {
			directions.add(Direction.Left);
		}
		
		if ((currPos.getRow() + 2 >= 0) && (currPos.getRow() + 2 < maze3d.getRows()) && 
				maze3d.getValue(currPos.getLevel(), currPos.getRow() + 2, currPos.getColm()) == maze3d.WALL) {
			directions.add(Direction.Forward);
		}
		
		if ((currPos.getRow() - 2 >= 0) && (currPos.getRow() - 2 < maze3d.getRows()) && 
				maze3d.getValue(currPos.getLevel(), currPos.getRow() - 2, currPos.getColm()) == maze3d.WALL) {
			directions.add(Direction.Backward);
		}
		
		if ((currPos.getLevel() + 2 >= 0) && (currPos.getLevel() + 2 < maze3d.getLevels()) && 
				maze3d.getValue(currPos.getLevel() + 2, currPos.getRow(), currPos.getColm()) == maze3d.WALL) {
			directions.add(Direction.Up);
		}
		
		if ((currPos.getLevel() - 2 >= 0) && (currPos.getLevel() - 2 < maze3d.getLevels()) && 
				maze3d.getValue(currPos.getLevel() - 2, currPos.getRow(), currPos.getColm()) == maze3d.WALL) {
			directions.add(Direction.Down);
		}
		
		return directions;
	}
}
