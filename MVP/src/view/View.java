package view;

import presenter.Properties;
import algorithms.mazeGenarators.Maze3d;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public interface View {
	/**
	 * Just display
	 * @param msg This is the msg to display
	 */
	void display(String msg);	
	/**
	 * 
	 */
	void start();
	/**
	 * Display Maze
	 * @param maze This is the maze object
	 */
	void displayMaze(Maze3d maze);
	/**
	 * Properties getter
	 * @param properties This is the properties object
	 */
	void setProperties(Properties properties);
	/**
	 * Returns if the maze is ready
	 * @param name This is the maze name
	 */
	void notifyMazeIsReady(String name);
}
