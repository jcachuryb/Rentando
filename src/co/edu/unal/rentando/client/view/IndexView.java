package co.edu.unal.rentando.client.view;

import co.edu.unal.rentando.client.presenter.IndexPresenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class IndexView extends Composite implements IndexPresenter.Display{

	Button btn;
	HorizontalPanel hp;
	
	public IndexView() {
		hp = new HorizontalPanel();
		btn = new Button("TEST");
		btn.getElement().addClassName("btn");
		hp.add(btn);
	}
	
	@Override
	public HasClickHandlers getButton() {
		// TODO Auto-generated method stub
		return btn;
	}
	
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return hp;
	}

}
