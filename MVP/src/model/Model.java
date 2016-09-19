package model;

import algorithms.mazeGenarators.Maze3d;

public interface Model {
	void generateMaze(String name, int levels, int rows, int cols);
	Maze3d getMaze(String name);
	void exit();	
}
