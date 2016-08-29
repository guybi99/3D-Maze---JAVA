package algorithms.demo;

import algorithms.mazeGenarators.GrowingTreeGenerator;
import algorithms.mazeGenarators.Maze3dGenerator;
import algorithms.mazeGenarators.Position;
import algorithms.mazeGenarators.Maze3d;
import algorithms.search.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

import io.*;

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

	public static void main1(String[] args) {
		Maze3dGenerator mg = new GrowingTreeGenerator();
		Maze3d maze = mg.generate(10,10,10);

		OutputStream out = null;
		
		try {
			out = new MyCompressorOutputStream( new FileOutputStream("1.maz") );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			out.write(maze.toByteArray());
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		InputStream in = null;
		try {
			in = new MyDecompressorInputStream( new FileInputStream("1.maz") );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		byte b[] = new byte[maze.toByteArray().length];
		
		try {
			in.read(b);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Maze3d loaded=new Maze3d(b);
		System.out.println(b.toString());
		System.out.println(loaded.equals(maze));

	}

}
