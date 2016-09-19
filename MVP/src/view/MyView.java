package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

public class MyView extends Observable implements View, Observer {
	
	private BufferedReader in;
	private PrintWriter out;
	private CLI cli;	

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
		// TODO Auto-generated method stub
		cli.start();
	}

	@Override
	public void display(String msg) {
		out.println(msg);
		out.flush();		
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == cli) {
			setChanged();
			notifyObservers(arg);
		}
	}

}
