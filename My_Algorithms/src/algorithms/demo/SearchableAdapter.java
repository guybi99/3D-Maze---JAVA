package algorithms.demo;

import java.util.ArrayList;

import algorithms.search.Searchable;
import algorithms.search.State;
import algorithms.mazeGenarators.*;

public class SearchableAdapter implements Searchable<Position> {
	private Maze3d m3d;
	
	public Maze3d getM3d() {
		return m3d;
	}
	public void setM3d(Maze3d m3d) {
		this.m3d = m3d;
	}
	public SearchableAdapter(Maze3d m3d)
	{
		this.m3d = m3d;
	}
	@Override
	public State<Position> getStartState()
	{
		return new State<Position>(this.m3d.getStartPosition());
	}
	@Override
	public State<Position> getGoalState()
	{
		return new State<Position>(this.m3d.getGoalPosition());
	}
	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> sp)
	{
		ArrayList<Position> ap =  this.m3d.getPossibleMoves(sp.getValue());
		ArrayList<State<Position>> asp = new ArrayList<State<Position>>() ;
		if(ap == null) return null ;
		for(Position p : ap)
		{
			asp.add(new State<Position>(p));
	    }
		
		return asp ;
	}
	@Override
	public double getMoveCost(State<Position> currState, State<Position> neighbor) {
		return 10;
	}

}