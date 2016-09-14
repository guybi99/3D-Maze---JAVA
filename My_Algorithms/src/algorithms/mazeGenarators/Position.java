package algorithms.mazeGenarators;

/**
 * @author Tal Mishaan 203908652 & Guy Binyamin 200958098
 *
 */
public class Position {
	private int row;
	private int level;
	private int colm;
	
	/**
	 * Constructor
	 * @param level int This is the level index
	 * @param row int This is the row index
	 * @param colm int This is the colm index
	 */
	public Position(int level, int row, int colm) {
		this.row = row;
		this.level = level;
		this.colm = colm;
	}
	/**
	 * Constructor
	 * @param pos Position This is the Position object
	 */
	public Position(Position pos) {
		this.row = pos.getRow();
		this.level = pos.getLevel();
		this.colm = pos.getColm();
	}

	/**
	 * This is getter for row index
	 * @return int This returns row index
	 */
	public int getRow() {
		return row;
	}
	/**
	 * This is getter for column index
	 * @return int This returns column index
	 */
	public int getColm() {
		return colm;
	}
	/**
	 * This is getter for level index
	 * @return int This returns level index
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * This sets level index
	 * @param level int This is the level index
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * This sets row index
	 * @param level int This is the row index
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * This sets column index
	 * @param level int This is the column index
	 */
	public void setColm(int colm) {
		this.colm = colm;
	}
	
	@Override
	public String toString(){
		return "{"+level+","+row+","+colm+"}";
	}
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() == this.getClass()){
			Position obj_cast = (Position)obj;
			
			if(obj_cast.getRow() == row && obj_cast.getColm() == colm && obj_cast.getLevel() == level){
				return true;
			}
		}
		return false;
	}
}