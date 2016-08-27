package boot;

import algorithms.mazeGenarators.*;
import java.util.Scanner;

public class Run {

	public static void main(String a, String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Levels of Maze= ");
		int levels = in.nextInt();
		
		System.out.print("Rows of Maze= ");
		int rows = in.nextInt();
		
		System.out.print("Columns of Maze= ");
		int coloums = in.nextInt();
		
		System.out.print("Type of Maze(Growing = 1, Simple = 2) = ");
		int type = in.nextInt();
		
		in.close();
		
		Maze3dAlgorithms [] algo = new Maze3dAlgorithms[3];
		algo[0] = new SimpleMaze3dGenerator();
		algo[1] = new GrowingTreeGenerator();
		
		Maze3d my_maze3d;
		
		if(type == 1){
			my_maze3d = algo[1].generate(levels, rows, coloums);
		}else{
			my_maze3d = algo[0].generate(levels, rows, coloums);
		}

		System.out.println("num of levels in the maze: "+my_maze3d.getLevels());
		System.out.println("num of rows in the maze: "+my_maze3d.getRows());
		System.out.println("num of coloums in the maze: "+my_maze3d.getColumns());
		System.out.println(my_maze3d.toString());
	
	}
}
