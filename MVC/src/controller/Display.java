package controller;

import model.MyModel;

/**
 * @author Tal Mishaan 203908652 & Guy Binyamin 200958098
 *
 */
public class Display implements Command {

	private MyModel m;
	
	public Display(MyModel m) {
		this.m = m;
	}

	@Override
	public void doCommand(String[] st) {
		m.display(st[1]);
	}

}
