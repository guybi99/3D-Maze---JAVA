package view;

import java.util.Observer;

import presenter.Properties;
import algorithms.mazeGenarators.Maze3d;

public interface View {
	void display(String msg);	
	void start();
	void displayMaze(Maze3d maze);
	void setProperties(Properties properties);
	void notifyMazeIsReady(String name);
}
