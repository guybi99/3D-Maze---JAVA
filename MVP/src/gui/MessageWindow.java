package gui;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;

import presenter.Properties;
import algorithms.mazeGenarators.Maze3d;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class MessageWindow extends BasicWindow
{

	public MessageWindow(String title, int width, int height) {
	}

	@Override
	public void start() {
		
	}

	@Override
	public void displayMaze(Maze3d maze) {
		
	}

	@Override
	public void setProperties(Properties properties) {
		
	}
	
	public void display(String x) {
		Text text = new Text(shell, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		text.setText(x);
	}

	@Override
	public void notifyMazeIsReady(String name) {
		
	}
	
	@Override
	protected void initWidgets() {
		shell.setLayout(new GridLayout(1,true));
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}

