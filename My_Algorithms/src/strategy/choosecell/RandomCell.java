package strategy.choosecell;

import java.util.Random;
import java.util.ArrayList;
import algorithms.mazeGenarators.Position;
import strategy.choosecell.CellChoice;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class RandomCell implements CellChoice {
	
	private Random rand = new Random();
	
	public Position getCell(ArrayList<Position> c){
		int random_cell = rand.nextInt(c.size());
		return c.get(random_cell);
	}
}