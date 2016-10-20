package gui;

import io.MyDecompressorInputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import presenter.Properties;
import view.View;
import algorithms.mazeGenarators.Maze3d;
import algorithms.mazeGenarators.Position;
import algorithms.search.Solution;
import algorithms.search.State;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public class MazeWindow extends BasicWindow implements View {
	private MazeDisplay mDisplay;
	final private Image imgbg = new Image(null, "resources/images/bg.jpg");
	public Text text = null;
	private Properties prop;
	private Label pos_label;
	
	public MazeWindow(Properties p) {
		this.prop = p;
	}
	
	@Override
	public void setProperties(Properties p) {
		prop = p;
	}
	
	@Override
	@SuppressWarnings("unused")
	protected void initWidgets() {
		GridLayout gl = new GridLayout(2, false);
		shell.setLayout(gl);				
		shell.setText("Maze3d");
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackgroundImage(imgbg);
		shell.setSize(750, 500);
		
		MazeMenu menu = new MazeMenu(this, prop);
		
		Rectangle b = display.getPrimaryMonitor().getBounds();
		Rectangle r = shell.getBounds();
		int y = b.y + (b.height - r.height) / 2;
		int x = b.x + (b.width - r.width) / 2;
		shell.setLocation(x, y);
		shell.addListener(SWT.Close, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				exit();
			}
		});
		
		pos_label = new Label(shell, SWT.VERTICAL);
		pos_label.setBackground(display.getSystemColor(SWT.COLOR_GRAY));
		pos_label.setText("Position: <None>");
		pos_label.setLayoutData(new GridData(SWT.BORDER, SWT.BORDER, true, false, 2, 1));
		
		RowLayout rowLay = new RowLayout(SWT.VERTICAL);
		Composite btnG = new Composite(shell, SWT.BORDER);
		btnG.setLayout(rowLay);
		
		Button generateBTN = new Button(btnG, SWT.PUSH);

		generateBTN.setText("Generate");	
		
		generateBTN.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				showGenerate();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		Button btnDisplayMaze = new Button(btnG, SWT.PUSH);
		btnDisplayMaze.setText("Play");	
		
		btnDisplayMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				showMaze("tal123");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		Button solveBTN = new Button(btnG, SWT.PUSH);
		solveBTN.setText("Solve");
		
		solveBTN.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				solveMaze("tal123");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		Button hintBTN = new Button(btnG, SWT.PUSH);
		hintBTN.setText("Hint");
		
		hintBTN.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				showMsg("The goal is at: "+mDisplay.getGoal().toString());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mDisplay = new MazeDisplay(shell, SWT.NONE,this,null);
		mDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		mDisplay.setFocus();
	}
	
	@Override
	public void notifyMazeIsReady(String name) {

		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				setChanged();
				notifyObservers("display_maze " + name);
			}
		});
	
	
	}

	public void showMSGBox(String str) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageBox msg = new MessageBox(shell, SWT.ICON_INFORMATION);
				msg.setMessage(str);
				msg.open();
			}
		});
	}
	

	@Override
	public void start() {
		run();		
	}

	public void showMsg(String msg) {
		showMSGBox(msg);
	}

	public void showMaze(String mazeByteArrString) {
		
		setChanged();
		notifyObservers("solve tal123 BFS");
		setChanged();
		notifyObservers("save_maze " + "tal123 tal123");
		
		MyDecompressorInputStream in = null;
		try {
			in = new MyDecompressorInputStream( new FileInputStream("tal123") );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
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
		
		Maze3d maze3d=new Maze3d(b);
		mDisplay.setMaze3d(maze3d);
		mDisplay.setBallPosition(maze3d.getStartPosition());
		pos_label.setText("Positon: "+maze3d.getStartPosition().toString());
		int[][] crossSection = maze3d.getCrossSectionByZ(maze3d.getStartPosition().getLevel());
		mDisplay.setCross(crossSection, null, null);
		mDisplay.setGoalPosition(maze3d.getGoalPosition());
		mDisplay.setMazeName("tal123");
		mDisplay.redrawMe();
	}
	
	protected void showGenerate() {
		Shell new_shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		new_shell.setText("Generate Window");
		new_shell.setLayout(new GridLayout(2, false));
		new_shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		new_shell.setSize(190, 190);
		new_shell.setBackgroundImage(new Image(null, "resources/images/sb.gif"));
		
		Rectangle r = new_shell.getBounds();
		Rectangle b = display.getPrimaryMonitor().getBounds();
		int y = b.y + (b.height - r.height) / 2;
		int x = b.x + (b.width - r.width) / 2;
		new_shell.setLocation(x, y);
		
		Label header = new Label(new_shell, SWT.BOLD);
		header.setText("Enter Dimensions");
		header.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		
		Label floors = new Label(new_shell, SWT.NONE);
		floors.setText("Levels: ");
		Text levels_text = new Text(new_shell, SWT.BORDER);
		levels_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		levels_text.setText("1");
		
		Label rows = new Label(new_shell, SWT.NONE);
		rows.setText("Rows: ");
		Text rows_text = new Text(new_shell, SWT.BORDER);
		rows_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		rows_text.setText("5");
		
		Label columns = new Label(new_shell, SWT.NONE);
		columns.setText("Columns: ");
		Text text_columns = new Text(new_shell, SWT.BORDER);
		text_columns.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		text_columns.setText("5");
		
		Button generate = new Button(new_shell, SWT.PUSH);
		generate.setText("Generate!");
		generate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		generate.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("generate_3d_maze" + " tal123 " + levels_text.getText() + " " + text_columns.getText() + " " + text_columns.getText() + " a");
				new_shell.dispose();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});

		new_shell.open();
		
		mDisplay.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				String direction = null;
				switch (e.keyCode) {
				case SWT.ARROW_DOWN:
					direction = "Backward";
					break;
				case SWT.ARROW_UP:
					direction = "Forward";
					break;
				case SWT.ARROW_RIGHT:
					direction = "Right";
					break;
				case SWT.PAGE_DOWN:
					direction = "Down";
					break;
				case SWT.PAGE_UP:
					direction = "Up";
					break;
				case SWT.ARROW_LEFT:
					direction = "Left";
					break;
				default:
					break;
				}
				if (direction != null) {
					Position pos = mDisplay.moveBall(direction);
					mDisplay.redrawMe();
					pos_label.setText("Positon: "+pos.toString());
				}
			}
		});
	}
	
	public void solveMaze(String name){
		setChanged();
		notifyObservers("solve "+name+" BFS");
//		setChanged();
//		notifyObservers("display_solution" + " " + name);
		
		List<State<Position>> a = p.getSolution(name).getStates();
		
		for( State<Position> pos : a){
			mDisplay.moveBall(pos.getValue());
			mDisplay.redrawMe();
			pos_label.setText("Positon: "+pos.toString());
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void display(String msg) {
	}

	@Override
	public void displayMaze(Maze3d maze) {
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	}

}
