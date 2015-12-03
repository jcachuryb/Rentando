package co.edu.unal.rentando.client.view;

import co.edu.unal.rentando.client.presenter.UserPresenter;
import co.edu.unal.rentando.shared.RentInfo;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserView extends CarListView implements UserPresenter.Display {

	FlowPanel mainPanel;
	Button rentButton;

	ScrollPanel carListContainer;

	public UserView() {
		mainPanel = new FlowPanel();
		rentButton = new Button("Rentar");
		mainPanel.add(rentButton);
		mainPanel.add(super.asWidget());
	}

	@Override
	public void transactionDone() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displaySingleCarView() {
		Window.alert("Display CarInfo");
	}

	@Override
	public void setRentInfo(RentInfo rent) {
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
	public CarListView getSuperView() {
		return this;
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return this.mainPanel;
	}
}
