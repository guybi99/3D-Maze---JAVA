package controller;

import model.MyModel;

public class DisplayCrossSectionBy implements Command {

	private MyModel m;
	
	public DisplayCrossSectionBy(MyModel m) {
		this.m = m;
	}

	@Override
	public void doCommand(String[] st) {
		m.getCrossBY(st[3], Integer.parseInt(st[1]), st[2].charAt(0));
	}

}
