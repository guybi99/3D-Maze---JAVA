package algorithms.mazeGenarators;

import java.util.Random;
import algorithms.mazeGenarators.Position;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class SimpleMaze3dGenerator extends Maze3dAlgorithms{
	private Random rand = new Random();
	
	private Maze3d maze3d;
	
	/**
	 * This method sets random walls in the maze in every point in the maze
	 */
	private void randomWalls(){
		// Random 0,1 and set for every pos
		
		for (int level = 0; level < maze3d.getLevels(); level++)
			for (int row = 0; row < maze3d.getRows(); row++)
				for (int colm = 0; colm < maze3d.getColumns(); colm++)
					maze3d.setValue(level, row, colm, rand.nextInt(2));
	}
	
	/**
	 * This method sets walls in the maze in all the border
	 * to ensure the maze will have frame
	 */
	public void buildFrame(){
		for (int level = 0; level < maze3d.getLevels(); level++)
			for(int row = 0; row < maze3d.getRows(); row++){
				maze3d.setWall(level, row, 0);
				maze3d.setWall(level, row, maze3d.getColumns()-1);
			}
		
		for (int col = 0; col < maze3d.getColumns(); col++)
			for(int row = 0; row < maze3d.getRows(); row++){
				maze3d.setWall(0, row, col);
				maze3d.setWall(maze3d.getLevels()-1, row, col);
			}
		
		for (int level = 0; level < maze3d.getLevels(); level++)
			for(int col = 0; col < maze3d.getColumns(); col++){
				maze3d.setWall(level, 0, col);
				maze3d.setWall(level, maze3d.getRows()-1, col);
			}
	}
	
	/**
	 * This method returns random position in the maze
	 * in order to set start and end position
	 * @return Position This returns random position in the maze
	 */
	public Position randomStartEndPositon(){
		// Random column not in frame (+1)
		int column = rand.nextInt(maze3d.getColumns()-2);
		column++;
		
		// Random row not in frame
		int row = rand.nextInt(maze3d.getRows()-2);
		row++;
		
		// Random level not in frame
		int level = rand.nextInt(maze3d.getLevels()-2);
		level++;
			
		return new Position(level, row, column);
	}
	
	/**
	 * This method sets free spaces from start to end
	 * to ensure a possible way out
	 * @param start This is the start Position of the maze
	 * @param end This is the end Position of the maze
	 */
	public void createPath(Position start, Position end){
		int level = start.getLevel();
		int row = start.getRow();
		
//		System.out.println(start.toString());
//		System.out.println(end.toString());
		
		// Make zeros through every level of start end points
		if(start.getLevel() < end.getLevel()){
			for (level = start.getLevel() ; level <= end.getLevel() ; level++)
				maze3d.setFree(level, start.getRow(), start.getColm());
			
			level--;
		}
		else{
			for (level = start.getLevel() ; level >= end.getLevel() ; level--)
				maze3d.setFree(level, start.getRow(), start.getColm());
			
			level++;
		}
		
		// Make zeros through every row of start end points
		if(start.getRow() < end.getRow()){
			for (row = start.getRow() ; row <= end.getRow() ; row++)
				maze3d.setFree(level, row, start.getColm());
			
			row--;
		}
		else{
			for (row = start.getRow() ; row >= end.getRow() ; row--)
				maze3d.setFree(level, row, start.getColm());
			
			row++;
		}

		// Make zeros through every column of start end points
		if(start.getColm() < end.getColm())
			for (int i = start.getColm() ; i <= end.getColm() ; i++)
				maze3d.setFree(level, row, i);
		else
			for (int i = start.getColm() ; i >= end.getColm(); i--)
				maze3d.setFree(level, row, i);
		
	}
	
	public Maze3d generate(int levels, int rows, int columns){
		this.maze3d = new Maze3d(levels+2, rows+2, columns+2); // With frame
		
		randomWalls(); // Put 0|1 on each pos
		buildFrame(); // Frame is wall
		
		Position startPos = randomStartEndPositon();
		Position endPos = startPos;
		
		// Assure start is not end
		while(startPos == endPos)
			endPos = randomStartEndPositon();
		
		maze3d.setStartPosition(startPos);
		maze3d.setGoalPosition(endPos);
		
		createPath(startPos, endPos); // creating a sure path to exit
		
		return maze3d;
		
	}
}
