package controller;

/**
 * @author Tal Mishaan 203908652 & Guy Binyamin 200958098
 *
 */
public interface Command {
	/**
	 * do the function we want with the parameters he have
	 * @param st the parameters we have
	 */
	public void doCommand(String[] st);
}
