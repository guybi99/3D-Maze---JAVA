package algorithms.search;

import java.util.List;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public interface Searchable<T> {
	/**
	 * This method is used to get searchable start
	 * @return State The start of searchable object
	 */
	State<T> getStartState();
	/**
	 * This method is used to get searchable goal
	 * @return State The goal of searchable object
	 */
	State<T> getGoalState();
	/**
	 * This method returns possible states list of nearby wanted state
	 * @param s This is the wanted state
	 * @return List This returns possible states list of nearby states
	 */
	List<State<T>> getAllPossibleStates(State<T> s);
	/**
	 * This method returns how much does it cost
	 * to get from currState to neighbor
	 * @param currState This is the initial state
	 * @param neighbor This is the nearby wanted state
	 * @return double This returns how much does it cost
	 * to get from currState to neighbor
	 */
	double getMoveCost(State<T> currState, State<T> neighbor);
}