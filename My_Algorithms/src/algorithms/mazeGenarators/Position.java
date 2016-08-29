package algorithms.mazeGenarators;

public class Position {
	private int row;
	private int level;
	private int colm;
	
	public Position(int level, int row, int colm) {
		this.row = row;
		this.level = level;
		this.colm = colm;
	}
	public Position(Position pos) {
		this.row = pos.getRow();
		this.level = pos.getLevel();
		this.colm = pos.getColm();
	}

	public int getRow() {
		return row;
	}
	public int getColm() {
		return colm;
	}
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public void setRow(int row) {
		this.row = row;
	}
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