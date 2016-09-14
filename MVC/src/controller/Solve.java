package controller;

import model.MyModel;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class Solve implements Command {

	private MyModel m;
	
	public Solve(MyModel m) {
		this.m = m;
	}

	@Override
	public void doCommand(String[] st) {
		m.solveM3d(st[1], st[2]);
	}

}
