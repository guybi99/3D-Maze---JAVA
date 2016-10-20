package gui;
import java.util.List;

import algorithms.mazeGenarators.Position;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenarators.Maze3d;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class MazeDisplay extends Canvas {
	
	@SuppressWarnings("unused")
	private String m_name;
	private int my_level_now;
	private int[][] cross = { {0}, {0} };
	private Character character;
	final private Image gate = new Image(null,"resources/images/goal.png") ;
	final private Image ronaldo_crying = new Image(null,"resources/images/winner.jpg");
	final private Image messi = new Image(null, "resources/images/wall.jpg");
	private boolean is_hint_draw;
	private Position hint_pos;
	private boolean winner;
	private Position goalPosition;
	

	private Maze3d maze3d;
	private MazeWindow mWindow;

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	
	public void setMazeName(String m) {
		this.m_name = m;
	}
	
	public void setGoalPosition(Position g) {
		this.goalPosition = g;
	}
	
	public Position getGoal() {
		return goalPosition;
	}
	
	public void drawHint(Position hintPos) {
		this.is_hint_draw = true;
		this.hint_pos = hintPos;
		redrawMe();
	}
	
	public void redrawMe() {
		getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				setEnabled(true);
				redraw();
				update();
			}
		});
	}

	public void setMyLevelNow(int i) {
		this.my_level_now = i;
	}

	public void setCross(int[][] c, List<Point> upHint, List<Point> downHint) {
		this.cross = c;
	}

	public void setBallPosition(Position p) {
		this.character.setPosistion(p);
	}

	public void moveCharacter(Position pos) {
		this.character.setPosistion(pos);
	}

	public Position getMovePosition(Position pos, String direction){
		Position nextPos = new Position(pos);
		
		switch(direction){
			case "Up":
				if(maze3d.getValue(pos.getLevel()+1, pos.getRow(), pos.getColm()) == 0){
					nextPos.setLevel(pos.getLevel()+1);					
				}
				break;
			case "Down":
				if(maze3d.getValue(pos.getLevel()-1, pos.getRow(), pos.getColm()) == 0){
					nextPos.setLevel(pos.getLevel()-1);
				}
				break;
			case "Forward":
				if(maze3d.getValue(pos.getLevel(), pos.getRow()-1, pos.getColm()) == 0){
					nextPos.setRow(pos.getRow()-1);
				}
				break;
			case "Backward":
				if(maze3d.getValue(pos.getLevel(), pos.getRow()+1, pos.getColm()) == 0){
					nextPos.setRow(pos.getRow()+1);
				}
				break;
			case "Right":
				if(maze3d.getValue(pos.getLevel(), pos.getRow(), pos.getColm()+1) == 0){
					nextPos.setColm(pos.getColm()+1);
				}
				break;
			case "Left":
				if(maze3d.getValue(pos.getLevel(), pos.getRow(), pos.getColm()-1) == 0){
					nextPos.setColm(pos.getColm()-1);
				}
				break;
			default:
				nextPos = null;
				break;
		}
		
		return nextPos;
	}
	
	public boolean possibleMoveFromPosition(Position pos,String direction){
		if (maze3d != null && getMovePosition(pos, direction) != null) {
			return true;
		}else
			return false;		
	}
	
	public Position moveBall(String direction) {
		if (possibleMoveFromPosition(character.getPosistion(), direction)) {
			Position nextPos = getMovePosition(character.getPosistion(), direction);
			int[][] crossSection = maze3d.getCrossSectionByZ(nextPos.getLevel());
			setCross(crossSection, null, null);
			setBallPosition(nextPos);
			return nextPos;			
		}else
			return null;
	}
	
	public Position moveBall(Position pos) {
		int[][] crossSection = maze3d.getCrossSectionByZ(pos.getLevel());
		setCross(crossSection, null, null);
		setBallPosition(pos);
		return pos;			
	}
	
	public void messageBox(String str) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageBox msg = new MessageBox(new Shell(),
						SWT.ICON_INFORMATION);
				msg.setMessage(str);
				msg.open();
			}
		});
	}

	public void setMaze3d(Maze3d maze) {
		this.maze3d = maze;
	}
	
	public MazeDisplay(Composite p, int s, MazeWindow mw, Maze3d m) {
		super(p, s);
		this.maze3d = m;
		this.mWindow = mw;
		
		character = new Character();
		
		if(character.getPosistion() != null)
			my_level_now = character.getPosistion().getLevel();
		character.setPosistion(new Position(-1, -1, -1));
		
		is_hint_draw = false;
		winner = false;
		goalPosition = new Position(-1, -1, -1);

		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				int x, y;
				int cellHeight = getSize().y / cross[0].length;
				int cellWidth = getSize().x / cross.length;
				

				e.gc.setForeground(new Color(null, 1, 255, 0));
				e.gc.setBackground(new Color(null, 0, 0, 0));
				
				my_level_now = character.getPosistion().getLevel();
				
				for (int i = 0; i < cross.length; i++) {
					for (int j = 0; j < cross[i].length; j++) {
						y = i * cellHeight;
						x = j * cellWidth;

							// e.gc.fillRectangle(z, y, cellWidth,cellHeight);
							
							if(cross[i][j] == 0){
								
							}else
								e.gc.drawImage(messi, 0, 0,messi.getBounds().width,messi.getBounds().height, x, y,cellWidth, cellHeight);
						}
					}
				
				if (is_hint_draw) {
					is_hint_draw = false;
					e.gc.drawImage(gate, 0, 0, gate.getBounds().width, gate.getBounds().height, (cellWidth * hint_pos.getLevel()) + (cellWidth / 4), (cellHeight * hint_pos.getRow()) + (cellHeight / 4), cellWidth/2, cellHeight/2);
				}
				
				if(character.getPosistion().getLevel() >= 0 &&
						character.getPosistion().getLevel() == goalPosition.getLevel() && 
						character.getPosistion().getRow() == goalPosition.getRow() && 
						character.getPosistion().getColm() == goalPosition.getColm() ){
					winner = true;
				}
				
				if (!winner) {
					character.drawing(cellWidth,cellHeight, e.gc);
					
					if(goalPosition.getLevel() == my_level_now)
						e.gc.drawImage(gate, 0, 0, gate.getBounds().width, gate.getBounds().height, cellWidth * goalPosition.getColm(), cellHeight * goalPosition.getRow(), cellWidth, cellHeight);
				} else{
					e.gc.drawImage(ronaldo_crying, 0, 0, ronaldo_crying.getBounds().width, ronaldo_crying.getBounds().height, cellWidth * goalPosition.getColm(), cellHeight * goalPosition.getRow(), cellWidth, cellHeight);
					winner = false;
				}
				
				forceFocus();
			}
		});
	}

}