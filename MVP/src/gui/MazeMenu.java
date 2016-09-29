package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import presenter.Properties;

public class MazeMenu {
  Display d;
  Shell s;
  Properties prop;
  
  @SuppressWarnings("unused")
  public MazeMenu(MazeWindow mazeWindow, Properties properties) {
    this.d = mazeWindow.display;
    this.s = mazeWindow.shell;
    this.prop = properties;
    
    Menu m = new Menu(s, SWT.BAR);

    final MenuItem file = new MenuItem(m, SWT.CASCADE);
    file.setText("File");
    final Menu filemenu = new Menu(s, SWT.DROP_DOWN);
    file.setMenu(filemenu);
    final MenuItem openItem = new MenuItem(filemenu, SWT.CASCADE);
    openItem.setText("Open");
    final Menu submenu = new Menu(s, SWT.DROP_DOWN);
    openItem.setMenu(submenu);
    final MenuItem childItem = new MenuItem(submenu, SWT.PUSH);
    childItem.setText("Properties");
    final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
    final MenuItem exitItem = new MenuItem(filemenu, SWT.PUSH);
    exitItem.setText("Exit");

    final MenuItem w = new MenuItem(m, SWT.CASCADE);
    w.setText("Window");
    final Menu wm = new Menu(s, SWT.DROP_DOWN);
    w.setMenu(wm);
    final MenuItem maxItem = new MenuItem(wm, SWT.PUSH);
    maxItem.setText("Maximize");
    final MenuItem minItem = new MenuItem(wm, SWT.PUSH);
    minItem.setText("Minimize");

    childItem.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        Shell parent = (Shell) maxItem.getParent().getParent();
        PropertiesPrint cs = new PropertiesPrint(parent);
      }

      public void widgetDefaultSelected(SelectionEvent e) {

      }
    });

    exitItem.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
    	  mazeWindow.exit();
      }

      public void widgetDefaultSelected(SelectionEvent e) {

      }
    });

    maxItem.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        Shell parent = (Shell) maxItem.getParent().getParent();
        parent.setMaximized(true);
      }

      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });

    minItem.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        Shell parent = (Shell) minItem.getParent().getParent();
        parent.setMaximized(false);
      }

      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });

    s.setMenuBar(m);
    
  }
  
  class PropertiesPrint {
		PropertiesPrint(Shell p) {
			Shell child = new Shell(p);
			child.setSize(200, 200);
			
			Text levels_text = new Text(child, SWT.BORDER);
			levels_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			levels_text.setText("1");
			
			child.setText("Properties");
			child.open();
		}
	}
}

