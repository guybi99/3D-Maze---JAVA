package controller;

import model.MyModel;

/**
 * @author Tal Mishaan 203908652 & Guy Binyamin 200958098
 *
 */
public class DisplayCrossSectionBy implements Command {

	private MyModel m;
	
	public DisplayCrossSectionBy(MyModel m) {
		this.m = m;
	}

	@Override
	public void doCommand(String[] st) {
		m.getCrossBY(st[3], Integer.parseInt(st[2]), st[1].charAt(0));
	}

}
