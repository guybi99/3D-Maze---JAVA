package strategy.choosecell;

import algorithms.mazeGenarators.Position;
import java.util.ArrayList;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public interface CellChoice {
	/**
	 * This method returns Position from Position list
	 * in a way choose to implements
	 * @param c ArrayList of position to choose position from
	 * @return Position This returns a position in a way choose to implement
	 */
	Position getCell(ArrayList<Position> c);
}