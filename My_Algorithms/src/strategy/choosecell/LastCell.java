package strategy.choosecell;

import algorithms.mazeGenarators.Position;
import strategy.choosecell.CellChoice;
import java.util.ArrayList;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class LastCell implements CellChoice {
	public Position getCell(ArrayList<Position> c){
		return c.get(1);
	}
}