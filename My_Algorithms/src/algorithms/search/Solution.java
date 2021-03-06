package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
@SuppressWarnings("serial")
public class Solution<T> implements Serializable{
	private List<State<T>> states = new ArrayList<State<T>>();

	/**
	 * States getter
	 * @return List This returns the states list
	 */
	public List<State<T>> getStates() {
		return states;
	}

	/**
	 * This method sets the state list
	 * @param states This is the states list
	 */
	public void setStates(List<State<T>> states) {
		this.states = states;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (State<T> s : states) {
			sb.append(s.toString()).append(" ");
		}
		return sb.toString();
	}
}