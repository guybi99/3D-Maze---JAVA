package model;

import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import algorithms.mazeGenarators.GrowingTreeGenerator;
import algorithms.mazeGenarators.Maze3d;

public class MyModel extends Observable implements Model {
	
	private ExecutorService executor;
	
	public MyModel() {
		executor = Executors.newFixedThreadPool(50);
	}		
		
	private Map<String, Maze3d> mazes = new ConcurrentHashMap<String, Maze3d>();
			
	@Override
	public void generateMaze(String name, int levels, int rows, int cols) {
		executor.submit(new Callable<Maze3d>() {

			@Override
			public Maze3d call() throws Exception {
				GrowingTreeGenerator generator = new GrowingTreeGenerator();
				Maze3d maze = generator.generate(levels, rows, cols);
				mazes.put(name, maze);
				
				setChanged();
				notifyObservers("maze_ready " + name);		
				return maze;
			}
			
		});
			
	}

	@Override
	public Maze3d getMaze(String name) {
		return mazes.get(name);
	}
	
	public void exit() {
		executor.shutdownNow();
	}
}
