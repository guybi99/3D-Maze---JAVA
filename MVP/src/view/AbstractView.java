package view;

import java.util.Observable;
import java.util.Observer;

import presenter.Presenter;
import presenter.Properties;

/**
 * @author Tal Mishaan 203908652 And Guy Binyamin 200958098
 *
 */
public abstract class AbstractView extends Observable implements View, Observer {
	
	public Presenter p;

	@Override
	public void addObserver1(Presenter pre) {
		addObserver(pre);
		this.p = pre;
	}
}
