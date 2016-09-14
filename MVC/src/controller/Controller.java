package controller;

/**
 * @author Tal Mishaan 203908652 & Guy Binyamin 200958098
 *
 */
public interface Controller {
	/**
	 * View results for 2d int array
	 * @param cross int[][] The 2D array of ints
	 */
	void viewResults(int[][] cross);
	/**
	 * View results for String
	 * @param cross String The String to view
	 */
	void viewResults(String str);
	/**
	 * View results for String array
	 * @param cross String[] The String array
	 */
	void viewResults(String[] arr);
}
