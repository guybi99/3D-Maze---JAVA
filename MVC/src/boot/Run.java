package boot;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import model.MyModel;
import view.MyView;
import controller.*;

public class Run {

	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		MyModel mm = new MyModel();
		HashMap<String,Command> hm = new HashMap<String,Command>();
		
		hm.put("dir", new Dir(mm));
		hm.put("generate_3d_maze", new Generate3DMaze(mm));
		hm.put("display", new Display(mm));
		hm.put("display_cross_section", new DisplayCrossSectionBy(mm));
		hm.put("save_maze", new SaveMaze(mm));
		hm.put("load_maze", new LoadMaze(mm));
		hm.put("solve", new Solve(mm));
		hm.put("display_solution", new DisplaySolution(mm));
		hm.put("exit", new Exit(mm));
		
		MyView mv = new MyView(in, out, hm);
		
		MyController c = new MyController(mv, mm, hm);
		mm.setC(c);
		mv.setController(c);
		
		c.getMv().start();
		
	}
}


