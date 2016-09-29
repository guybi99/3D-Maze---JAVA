package gui;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import algorithms.mazeGenarators.Position;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class Character {
	private Position position;
	private Image image;
	
	public Character() {
		this.image = new Image(null, "resources/images/player.png");
	}

	public Position getPosistion() {
		return position;
	}

	public void setPosistion(Position position) {
		this.position = position;
	}
	
	public void drawing(int cellWidth, int cellHeight, GC gc) {
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, cellWidth * position.getColm(), cellHeight * position.getRow(), cellWidth, cellHeight);
	}
	
}