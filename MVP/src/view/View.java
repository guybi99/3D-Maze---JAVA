package view;

import presenter.Properties;
import algorithms.mazeGenarators.Maze3d;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public interface View {
	void display(String msg);	
	void start();
	void displayMaze(Maze3d maze);
	void setProperties(Properties properties);
	void notifyMazeIsReady(String name);
}
