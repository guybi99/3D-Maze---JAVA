package controller;

import java.util.HashMap;

import model.MyModel;
import view.MyView;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class MyController implements Controller {
	private MyView mv;
	private MyModel mm;
	private HashMap<String,Command> hm;

	/**
	 * This method return the view object
	 * @return MyView The view object
	 */
	public MyView getMv() {
		return mv;
	}
	
	/**
	 * This method return the model object
	 * @return MyModel The model object
	 */
	public MyModel getMm() {
		return mm;
	}

	/**
	 * This method return the String Command hash map
	 * @return HashMap This is the String Command hash map
	 */
	public HashMap<String, Command> getHm() {
		return hm;
	}
	
	/**
	 * Constructor
	 * @param mv MyView The view object
	 * @param mm MyModel The model object
	 * @param hm HashMap The String Command hash map
	 */
	public MyController(MyView mv, MyModel mm, HashMap<String, Command> hm) {
		super();
		this.mv = mv;
		this.mm = mm;
		this.hm = hm;
	}
	@Override
	public void viewResults(String[] arr) {
		for(String a : arr)
			mv.printResults(a);
	}
	@Override
	public void viewResults(String str) {
		mv.printResults(str);
	}

	@Override
	public void viewResults(int[][] cross) {
		mv.printResults(cross);
	}
	
}