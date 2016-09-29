package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

import presenter.Properties;
import algorithms.mazeGenarators.Maze3d;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class MyView extends AbstractView {
	
	private BufferedReader in;
	private PrintWriter out;
	private CLI cli;
	Properties prop;

	public MyView(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
				
		cli = new CLI(in, out);
		cli.addObserver(this);
	}
	
	public BufferedReader getIn() {
		return in;
	}



	public void setIn(BufferedReader in) {
		this.in = in;
	}



	public PrintWriter getOut() {
		return out;
	}



	public void setOut(PrintWriter out) {
		this.out = out;
	}



	
	@Override
	public void start() {
		cli.start();
	}

	@Override
	public void display(String msg) {
		out.print(msg+" ");
		out.flush();		
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == cli) {
			setChanged();
			notifyObservers(arg);
		}
	}

	@Override
	public void displayMaze(Maze3d maze) {
		System.out.println(maze.toString());;
	}

	@Override
	public void setProperties(Properties properties) {
		prop = properties;
	}

	@Override
	public void notifyMazeIsReady(String name) {
		// TODO Auto-generated method stub
		
	}

}
