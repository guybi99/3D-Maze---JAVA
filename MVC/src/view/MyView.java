package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;
import controller.Controller;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class MyView implements View {

	CLI cli;
	Controller controller;
	
	/**
	 * Constructor
	 * @param in BufferedReader The object to read input from
	 * @param out PrintWriter The object to write output to
	 * @param hms HashMap The String Command hash map
	 */
	public MyView(BufferedReader in, PrintWriter out, HashMap<String,Command> hm) {
		super();
		this.cli = new CLI(in, out, hm);
	}
	
	/**
	 * Controller getter
	 * @return Controller This is the controller object
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * Controller setter
	 * @param controller Controller This is the controller to set
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void start() {
		cli.start();
	}
	
	public void printResults(String str) {
		System.out.println("- "+str);
	}
	
	public void printResults(int[][] cross){
		for( int[] x : cross){
			for( int y : x)
				System.out.print(y+" ");
			
			System.out.println();
		}
	}
}
