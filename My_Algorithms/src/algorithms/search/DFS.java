package algorithms.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class DFS<T> extends CommonSearcher<T> {
	private ArrayList<State<T>> closedList = new ArrayList<State<T>>();
	
	@Override
	public Solution<T> search(Searchable<T> s) {
		State<T> startState = s.getStartState();
		
		return RecursiveDFS(s, startState);
	}
	
	/**
	 * This method recursively search the end of Searchable object
	 * from the start of the object
	 * @param s This is the Searchable object
	 * @param currentState This is the current state running on
	 * @return Solution This returns the Solution of the Searchable object
	 */
	public Solution<T> RecursiveDFS(Searchable<T> s, State<T> currentState){
		closedList.add(currentState);
		
		if (currentState.equals(s.getGoalState())) {
			return backTrace(currentState);
		}
			
		List<State<T>> neighbors = s.getAllPossibleStates(currentState);

		for (State<T> neighbor : neighbors){
			if(!closedList.contains(neighbor)){
				evaluatedNodes++;
				neighbor.setCameFrom(currentState);
				neighbor.setCost(currentState.getCost() + s.getMoveCost(currentState, neighbor));
				
                RecursiveDFS(s, neighbor);
			}
		}
		
		return null;
	}
	
	/**
	 * This method check if a specific state is in the closed list
	 * @param state This is the state
	 * @return Boolean This returns true if state in closed list
	 */
	public boolean ArrayContains(State<T> state){
        for(int i=0; i < closedList.size(); i++){
            if(closedList.get(i).equals(state))
                return true;
        }
        return false;
    }

}
