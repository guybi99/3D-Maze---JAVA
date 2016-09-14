package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import model.MyModel;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class SaveMaze implements Command {

	private MyModel m;
	
	public SaveMaze(MyModel m) {
		this.m = m;
	}
	
	@Override
	public void doCommand(String[] st) {
		try {
			m.saveM3d(st[1], new FileOutputStream (st[2]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
