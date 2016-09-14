package controller;

import java.io.File;
import model.MyModel;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class Dir implements Command {

	private MyModel m;
	
	public Dir(MyModel m) {
		this.m = m;
	}

	@Override
	public void doCommand(String[] st) {
		m.dir(new File(st[1]));
	}

}
