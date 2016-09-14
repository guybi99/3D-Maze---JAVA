package algorithms.mazeGenarators;

/**
 * @author Tal Mishaan 203908652 & Guy Binyamin 200958098
 *
 */
public interface Maze3dGenerator {
	/**
	 * This method returns a Maze3d object
	 * with wanted levels, rows and columns
	 * @param levels This is the wanted levels
	 * @param rows This is the wanted rows
	 * @param colms This is the wanted columns
	 * @return Maze3d This returns a Maze3d object
	 */
	Maze3d generate(int levels, int rows, int colms);
	/**
	 * This method returns a String of milliseconds
	 * took to generate a Maze3d Object
	 * with wanted levels, rows and columns
	 * @param levels This is the wanted levels
	 * @param rows This is the wanted rows
	 * @param colms This is the wanted columns
	 * @return String This returns a string of milliseconds took to generate
	 */
	String measureAlgorithmTime(int levels, int rows, int colms);
}
