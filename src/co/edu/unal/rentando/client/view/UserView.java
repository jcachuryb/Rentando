package co.edu.unal.rentando.client.view;

import java.util.List;

import co.edu.unal.rentando.client.presenter.UserPresenter.Display;
import co.edu.unal.rentando.shared.CarInfo;
import co.edu.unal.rentando.shared.RentInfo;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserView extends Composite implements Display {

	LayoutPanel container; 
	Button rentButton;
	
	public UserView() {
		container = new LayoutPanel();
		rentButton = new Button("Extremoduro");
		container.add(rentButton);
	}

	@Override
	public void setRentInfo(RentInfo rent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCarList(List<CarInfo> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public RentInfo getRenInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasClickHandlers getRentButton() {
		// TODO Auto-generated method stub
		return rentButton;
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return rentButton;
	}
}
