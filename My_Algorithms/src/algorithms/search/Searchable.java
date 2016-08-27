package algorithms.search;

import java.util.List;

public interface Searchable<T> {
	State<T> getStartState();
	State<T> getGoalState();
	List<State<T>> getAllPossibleStates(State<T> s);
	double getMoveCost(State<T> currState, State<T> neighbor);
}