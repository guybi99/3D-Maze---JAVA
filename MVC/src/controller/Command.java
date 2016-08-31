package controller;

public interface Command {
	/**
	 * do the function we want with the parameters he have
	 * @param st the parameters we have
	 */
	public void doCommand(String[] st);
}
