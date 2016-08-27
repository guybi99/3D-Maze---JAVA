package algorithms.mazeGenarators;

import algorithms.mazeGenarators.Position;
import java.util.ArrayList;

public class Maze3d {
	private int[][][] maze3d;
	private int levels;
	private int rows;
	private int columns;
	private Position start;
	private Position goal;
	
	public final int FREE = 0;
	public final int WALL = 1;

	public Maze3d(int[][][] maze3d) {
		super();
		this.maze3d = maze3d;
	}
	public Maze3d(int level, int row, int col) {
		super();
		levels = level;
		rows = row;
		columns = col;
		
		this.maze3d = new int[levels][rows][columns];
		
//		for (int[][] z : this.maze3d)
//			for(int[] y : z)
//				Arrays.fill(y, 1);
		
	}	
	
	public int[][][] getMaze3d() {
		return maze3d;
	}
	public int getLevels() {
		return levels;
	}
	public int getRows() {
		return rows;
	}
	public int getColumns() {
		return columns;
	}
	public int getValue(int level, int row, int col){
		return this.maze3d[level][row][col];
	}
	public Position getStartPosition() {
		return start;
	}
	public Position getGoalPosition() {
		return goal;
	}
	public ArrayList<Position> getPossibleMoves(Position p){
		ArrayList<Position> possible_moves = new ArrayList<Position>();
		int level = p.getLevel();
		int row = p.getRow();
		int col = p.getColm();
		
		if((level+1 < this.levels) && getValue(level+1, row, col) == 0){
			possible_moves.add(new Position(level+1, row, col)); // Up
		}
		if((level-1 >= 0) && getValue(level-1, row, col) == 0){
			possible_moves.add(new Position(level-1, row, col)); // Down
		}
		if((row+1 < this.rows) && getValue(level, row+1, col) == 0){
			possible_moves.add(new Position(level, row+1, col)); // Forward
		}
		if((row-1 < this.rows) && getValue(level, row-1, col) == 0){
			possible_moves.add(new Position(level, row-1, col)); // Backward
		}
		if((col+1 < this.columns) && getValue(level, row, col+1) == 0){
			possible_moves.add(new Position(level, row, col+1)); // Right
		}
		if((col-1 < this.columns) && getValue(level, row, col-1) == 0){
			possible_moves.add(new Position(level, row, col-1)); // Left
		}
		
		return possible_moves;
	}
	public int[][] getCrossSectionByX(int x){
		if(x < columns && x >= 0){
			int[][] maze2d = new int[levels][rows];
			
			for (int i = 0; i < levels; i++) {
				for (int j = 0; j < rows; j++) {
					maze2d[i][j] = this.getValue(i, j, x);
				}
			}
			
			return maze2d;
		}else{
			throw(new IndexOutOfBoundsException("Value is not in bounds"));
		}
	}
	public int[][] getCrossSectionByY(int y){
		if(y < rows && y >= 0){
			int[][] maze2d = new int[levels][columns];
			
			for (int i = 0; i < levels; i++) {
				for (int j = 0; j < columns; j++) {
					maze2d[i][j] = this.getValue(i, y, j);
				}
			}

			return maze2d;
		}else{
			throw(new IndexOutOfBoundsException("Value is not in bounds"));
		}
	}
	public int[][] getCrossSectionByZ(int z){
		if(z < levels && z >= 0){
			int[][] maze2d = new int[rows][columns];
			
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					maze2d[i][j] = this.getValue(z, i, j);
				}
			}
			
			return maze2d;
		}else{
			throw(new IndexOutOfBoundsException("Value is not in bounds"));
		}
	}
	
	public void setStartPosition(Position startPosition) {
		this.start = startPosition;
		this.setFree(startPosition);
	}
	public void setGoalPosition(Position goalPosition) {
		this.goal = goalPosition;
	}
	public void setWall(int level, int row, int col){
		this.maze3d[level][row][col] = WALL;
	}
	public void setWall(Position pos){
		this.maze3d[pos.getLevel()][pos.getRow()][pos.getColm()] = WALL;
	}
	public void setFree(int level, int row, int col){
		this.maze3d[level][row][col] = FREE;
	}
	public void setFree(Position pos){
		this.maze3d[pos.getLevel()][pos.getRow()][pos.getColm()] = FREE;
	}
	public void setMaze3d(int[][][] maze3d) {
		this.maze3d = maze3d;
	}
	public void setValue(int level, int row, int col, int value){
		this.maze3d[level][row][col] = value;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int h = 0; h < this.levels; h++){
			sb.append("\n\n");
			
			for (int i = 0; i < this.rows; i++) {
				for (int j = 0; j < this.columns; j++) {
					if(h == start.getLevel() && i == start.getRow() && j == start.getColm())
						sb.append("S ");
					else if(h == goal.getLevel() && i == goal.getRow() && j == goal.getColm())
						sb.append("X ");
					else
						sb.append(this.maze3d[h][i][j] + " ");
				}
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}
	
}
