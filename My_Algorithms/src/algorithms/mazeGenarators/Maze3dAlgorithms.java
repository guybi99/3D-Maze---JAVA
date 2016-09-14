package algorithms.mazeGenarators;

/**
 * @author Tal Mishaan 203908652 & Guy Binyamin 200958098
 *
 */
public abstract class Maze3dAlgorithms implements Maze3dGenerator{
	
	public String measureAlgorithmTime(int levels, int rows, int colms){
		
		long start = System.currentTimeMillis();
		generate(levels, rows, colms);
		long end = System.currentTimeMillis();
		
		return String.valueOf(end-start);
	}
}
