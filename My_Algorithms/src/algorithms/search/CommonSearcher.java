package algorithms.search;

import java.util.List;

public abstract class CommonSearcher<T> implements Searcher<T>{
	protected int evaluatedNodes;

	@Override
	public int getNumberOfNodesEvaluated() {		
		return evaluatedNodes;
	}
	
	/**
	* This method is used to build the Solution object
	* @param goalState This is the final state.
	* @return Solution<T> This returns Solution object with list of states from start to end.
	*/
	protected Solution<T> backTrace(State<T> goalState) {
		Solution<T> sol = new Solution<T>();
		State<T> currState = goalState;
		List<State<T>> states = sol.getStates();
		
		while (currState != null) {
			states.add(0, currState);
			currState = currState.getCameFrom();
		}
		
		sol.setStates(states);
		
		return sol;
	}

}