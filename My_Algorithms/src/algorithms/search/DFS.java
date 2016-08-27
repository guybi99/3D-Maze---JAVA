package algorithms.search;

import java.util.ArrayList;
import java.util.List;

public class DFS<T> extends CommonSearcher<T> {
	private ArrayList<State<T>> closedList = new ArrayList<State<T>>();
	
	@Override
	public Solution<T> search(Searchable<T> s) {
		State<T> startState = s.getStartState();
		
		return RecursiveDFS(s, startState);
	}
	
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
	
	public boolean ArrayContains(State<T> state){
        for(int i=0; i < closedList.size(); i++){
            if(closedList.get(i).equals(state))
                return true;
        }
        return false;
    }

}
