package gui;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import view.AbstractView;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public abstract class BasicWindow extends AbstractView implements Runnable {

	protected Display display;
	protected Shell shell;
	protected abstract void initWidgets();
	
	@Override
	public void run() {
		display = Display.getCurrent();
		shell = new Shell(display);
		initWidgets();
		
		shell.open();
		
		while(!shell.isDisposed()){
		   if(!display.readAndDispatch()){
		      display.sleep(); 
		   }
		
		}
		display.dispose();
	}
	
	public void exit(){
		shell.dispose();
		display.dispose();
		System.exit(0);
	}

}
