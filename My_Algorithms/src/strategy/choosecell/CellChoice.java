package strategy.choosecell;

import algorithms.mazeGenarators.Position;
import java.util.ArrayList;

public interface CellChoice {
	Position getCell(ArrayList<Position> c);
}