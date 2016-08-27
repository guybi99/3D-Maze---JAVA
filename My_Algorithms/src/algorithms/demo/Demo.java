package algorithms.demo;

import algorithms.mazeGenarators.GrowingTreeGenerator;
import algorithms.mazeGenarators.Maze3dGenerator;
import algorithms.mazeGenarators.Position;
import algorithms.search.*;

public class Demo {
	private static void run(Maze3dGenerator mg)
	{
		
		SearchableAdapter maze = new SearchableAdapter(mg.generate(10,10,10));
		
//		System.out.println(maze.getM3d().toString());
		
		Searcher<Position> bfs = new BFS<Position>();
		bfs.search(maze);

		System.out.println("BFS:");
		System.out.println(bfs.getNumberOfNodesEvaluated());
		
		Searcher<Position> dfs = new DFS<Position>();
		dfs.search(maze);

		System.out.println("DFS:");
		System.out.println(dfs.getNumberOfNodesEvaluated());
	}

	public static void main(String[] args) {
		run(new GrowingTreeGenerator());
	}

}
