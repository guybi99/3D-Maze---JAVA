package algorithms.mazeGenarators;

public abstract class Maze3dAlgorithms implements Maze3dGenerator{
	
	public String measureAlgorithmTime(int levels, int rows, int colms){
		
		long start = System.currentTimeMillis();
		generate(levels, rows, colms);
		long end = System.currentTimeMillis();
		
		return String.valueOf(end-start);
	}
}
