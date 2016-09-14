package controller;

import model.MyModel;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class Exit implements Command {

	private MyModel m;
	
	public Exit(MyModel m) {
		this.m = m;
	}

	@Override
	public void doCommand(String[] st) {
		m.exit();
	}

}
