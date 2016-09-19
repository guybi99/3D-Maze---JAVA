package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;

public class CLI extends Observable {
	private BufferedReader in;
	private PrintWriter out;	
	
	public CLI(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;		
	}
	
	private void printMenu() {
		// TODO
		out.print("Choose command: (");
		out.flush();
	}
	
	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
				
					printMenu();
					
					try {
						String commandLine = in.readLine();
						setChanged();
						notifyObservers(commandLine);
						
						if (commandLine.equals("exit"))
							break;
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}			
		});
		thread.start();		
	}
	
}
