package model;

import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import controller.Command;
import controller.Controller;
import algorithms.demo.SearchableAdapter;
import algorithms.mazeGenarators.*;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import algorithms.search.State;

public class MyModel implements Model{

	Controller c;
	private HashMap<String,Maze3d> maze_hm = new HashMap<String, Maze3d>();
	private HashMap<String,Solution<Position>> sol_hm = new HashMap<String, Solution<Position>>();

	public Controller getC() {
		return c;
	}
	public void setC(Controller c) {
		this.c = c;
	}

	@Override
	public void dir(File dir) {
		String[] arr;
		 if(dir.exists())
		 {
			 arr = dir.list();
			 c.viewResults(arr);
		 }
		 else
		 {
			 c.viewResults("The path " + dir.getPath() + " does not exists");
		 }
	}

	@Override
	public void getCrossBY(String name, int index, char place) {
		if(maze_hm.containsKey(name)){
			try{
				int[][] cross = new int[0][0];
				
				switch(place){
				case 'x':
					cross = maze_hm.get(name).getCrossSectionByX(index);
					break;
				case 'y':
					cross = maze_hm.get(name).getCrossSectionByY(index);
					break;
				case 'z':
					cross = maze_hm.get(name).getCrossSectionByZ(index);
					break;
				default:
					c.viewResults("The place " + place + " does not exists");
				}
				
				c.viewResults(cross);
			}catch(IndexOutOfBoundsException e){
				e.printStackTrace();
			}
		}else{
			c.viewResults("Maze "+name+" doesn't exists!");
		}
	}

	@Override
	public void generateM3d(String name, int x, int y, int z, String algo) {
		Maze3d maze3d = null;
		Maze3dGenerator mg = null;
		
		switch(algo){
		case "Simple":
			mg = new SimpleMaze3dGenerator();
			maze3d = mg.generate(z, y, x);
			maze_hm.put(name, maze3d);
			c.viewResults(name+" has been generated!");
			break;
		case "Growing":
			mg = new GrowingTreeGenerator();
			maze3d = mg.generate(z, y, x);
			maze_hm.put(name, maze3d);
			c.viewResults(name+" has been generated!");
			break;
		default:
			c.viewResults("The algorithm " + algo + " does not exists");
		}
	}

	@Override
	public void display(String name) {
		if(maze_hm.containsKey(name)){
			c.viewResults(maze_hm.get(name).toString());
		}else{
			c.viewResults("Maze "+name+" doesn't exists!");
		}
	}

	@Override
	public void saveM3d(String name, FileOutputStream fos) {
		if(maze_hm.containsKey(name)){
			OutputStream out = new MyCompressorOutputStream(fos);
			
			try {
				out.write(maze_hm.get(name).toByteArray());
				out.flush();
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else{
			c.viewResults("Maze "+name+" doesn't exists!");
		}
		
	}

	@Override
	public void loadM3d(FileInputStream fis, String name) {
		MyDecompressorInputStream in = new MyDecompressorInputStream( fis );

		int size = 0;
		try {
			size = in.getSize();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		byte b[] = new byte[size];
		
		try {
			in.read(b);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Maze3d maze3d = new Maze3d(b);
		maze_hm.put(name, maze3d);
		
	}
	
	@Override
	public void solveM3d(String name, String algo) {
		if(maze_hm.containsKey(name)){
			SearchableAdapter sa = new SearchableAdapter(maze_hm.get(name));
			if(algo.equals("BFS")){
				Searcher<Position> bfs = new BFS<Position>();
				Solution<Position> solution = bfs.search(sa);
				sol_hm.put(name, solution);
				c.viewResults("Solution for "+name+" is ready!");
			}else if(algo.equals("DFS")){
				Searcher<Position> dfs = new BFS<Position>();
				Solution<Position> solution = dfs.search(sa);
				sol_hm.put(name, solution);
				c.viewResults("Solution for "+name+" is ready!");
			}else{
				c.viewResults("Algorithm "+algo+" doesn't exists!");
			}
		}else{
			c.viewResults("Maze "+name+" doesn't exists!");
		}
	}

	@Override
	public void displaySolution(String name) {
		if(sol_hm.containsKey(name)){
			List<State<Position>> positions = sol_hm.get(name).getStates();
			for( State<Position> pos : positions){
				c.viewResults(pos.getValue().toString());
			}
		}else{
			c.viewResults("Solution for "+name+" doesn't exists!");
		}
	}

	@Override
	public void exit() {
		c.viewResults("Bye!");
		
	}
}
