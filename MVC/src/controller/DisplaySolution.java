package controller;

import model.MyModel;

/**
 * @author Tal Mishaan 203908652 & Guy Binyamin 200958098
 *
 */
public class DisplaySolution implements Command {

	private MyModel m;
	
	public DisplaySolution(MyModel m) {
		this.m = m;
	}

	@Override
	public void doCommand(String[] st) {
		m.displaySolution(st[1]);
	}

}
