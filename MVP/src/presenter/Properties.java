package presenter;

import java.io.Serializable;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class Properties implements Serializable {
	private static final long serialVersionUID = 1L;
	private int numOfThreads;
	private String generateMazeAlgorithm;
	private String solveMazeAlgorithm;
	private int isGUI;
	
	public Properties() {
	}
			
	public int getNumOfThreads() {
		return numOfThreads;
	}
	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}
	public String getGenerateMazeAlgorithm() {
		return generateMazeAlgorithm;
	}
	public void setGenerateMazeAlgorithm(String generateMazeAlgorithm) {
		this.generateMazeAlgorithm = generateMazeAlgorithm;
	}
	public String getSolveMazeAlgorithm() {
		return solveMazeAlgorithm;
	}
	public void setSolveMazeAlgorithm(String solveMazeAlgorithm) {
		this.solveMazeAlgorithm = solveMazeAlgorithm;
	}

	public int getIsGUI() {
		return isGUI;
	}

	public void setIsGUI(int i) {
		this.isGUI = i;
	}

	@Override
	public String toString() {
		return "Properties [numOfThreads=" + numOfThreads + ", generateMazeAlgorithm=" + generateMazeAlgorithm
				+ ", solveMazeAlgorithm=" + solveMazeAlgorithm + ", isGUI=" + isGUI + "]";
	}
	
	
}
