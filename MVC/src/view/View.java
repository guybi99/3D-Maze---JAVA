package view;

/**
 * @author Tal Mishaan 203908652 & Guy Binyamin 200958098
 *
 */
public interface View {
	/**
	 * Print 2d int array
	 * @param cross int[][] This is the 2d int arrays
	 */
	void printResults(int[][] cross);
	/**
	 * Print String
	 * @param cross String This is the String
	 */
	void printResults(String str);
	/**
	 * Start working
	 */
	void start();
}
