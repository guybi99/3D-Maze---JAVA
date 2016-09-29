package algorithms.search;

import static org.junit.Assert.*;
import org.junit.Test;
import algorithms.demo.SearchableAdapter;
import algorithms.mazeGenarators.Maze3d;
import algorithms.mazeGenarators.Position;

public class BFSTest<T> {

	Position goalPosition;
	Maze3d maze3d;
	int[][][] maze;
	Position startPosition;
	SearchableAdapter searchable;
	Solution<Position> sol;
	Searcher<Position> bfs;
	
	
	public BFSTest(){
		maze = new int[][][] {
				{
					{ 1, 1, 1, 1, 1, 1, 1, }, 
					{ 1, 1, 1, 1, 1, 1, 1, },
					{ 1, 1, 1, 1, 1, 1, 1, },
					{ 1, 1, 1, 1, 1, 1, 1, },
					{ 1, 1, 1, 1, 1, 1, 1, },
					{ 1, 1, 1, 1, 1, 1, 1, },
					{ 1, 1, 1, 1, 1, 1, 1, },
				},
				{
					{ 1, 1, 1, 1, 1, 1, 1, },
					{ 1, 1, 0, 0, 0, 0, 1, },
					{ 1, 0, 0, 1, 0, 1, 1, },
					{ 1, 0, 0, 0, 0, 0, 1, },
					{ 1, 1, 1, 0, 0, 0, 1, },
					{ 1, 1, 0, 1, 1, 0, 1, },
					{ 1, 1, 1, 1, 1, 1, 1, },
				},
				{
					{ 1, 1, 1, 1, 1, 1, 1, },
					{ 1, 0, 0, 0, 1, 1, 1, },
					{ 1, 0, 1, 1, 0, 0, 1, },
					{ 1, 0, 0, 0, 0, 1, 1, },
					{ 1, 1, 0, 1, 0, 1, 1, },
					{ 1, 0, 1, 0, 0, 0, 1, },
					{ 1, 1, 1, 1, 1, 1, 1, },
				},
				{
					{ 1, 1, 1, 1, 1, 1, 1, },
					{ 1, 1, 1, 1, 1, 1, 1, },
					{ 1, 1, 1, 1, 1, 1, 1, },
					{ 1, 1, 1, 1, 1, 1, 1, },
					{ 1, 1, 1, 1, 1, 1, 1, },
					{ 1, 1, 1, 1, 1, 1, 1, },
					{ 1, 1, 1, 1, 1, 1, 1, },
				}
		};
		
		startPosition = new Position(1, 3, 4);
		goalPosition = new Position(2, 5, 4);

		maze3d = new Maze3d(maze);
		maze3d.setStartPosition(startPosition);
		maze3d.setGoalPosition(goalPosition);
		
		bfs = new BFS<Position>();
		searchable = new SearchableAdapter(maze3d);
		sol = bfs.search(searchable);
	}
	
	@Test
	public void checkSizeOfSolution() {
		
		assertEquals(4, sol.getStates().size());
	}
	@Test
	public void checkStartIsFirst() {
		assertEquals(startPosition, sol.getStates().get(0).getValue());
	}
	@Test
	public void checkGoalStateIsLast() {
		assertEquals(goalPosition, sol.getStates().get(sol.getStates().size()-1).getValue());
	}
	@Test
	public void testEvaluted() {
		assertEquals(true, bfs.getNumberOfNodesEvaluated() >= sol.getStates().size());
	}

}
