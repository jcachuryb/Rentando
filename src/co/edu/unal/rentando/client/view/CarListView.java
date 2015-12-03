package co.edu.unal.rentando.client.view;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.client.custom.CarListItem;
import co.edu.unal.rentando.client.presenter.CarListPresenter;
import co.edu.unal.rentando.shared.CarInfo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class CarListView extends Composite implements
		CarListPresenter.Display {

	private CarInfo currentCar;
	protected ScrollPanel scroll;
	protected FlowPanel panel;
	private List<CarListItem> carList;

	public CarListView() {
		panel = new FlowPanel();
		scroll = new ScrollPanel();
		scroll.setHeight("500px");
		carList = new ArrayList<CarListItem>();
		scroll.add(panel);
	}

	@Override
	public void fillCarList(List<CarInfo> list) {
		carList.clear();
		panel.clear();
		for (CarInfo carInfo : list) {
			CarListItem item = new CarListItem();
			item.fillCarInfo(carInfo);
			item.setClickHandler(new CarListClickHandler(carInfo));
			carList.add(item);
			panel.add(item.getWidget());
		}
	}

	public void setCurrentCar(CarInfo car) {
		currentCar = car;
	}

	@Override
	public CarInfo getCurrentcar() {
		// TODO Auto-generated method stub
		return currentCar;
	}

	@Override
	public abstract void transactionDone();

	public List<CarListItem> getCarList() {
		return carList;
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return scroll;
	}

	public void setCarList(List<CarListItem> carList) {
		this.carList = carList;
	}

	class CarListClickHandler implements ClickHandler {

		private final CarInfo carInfo;

		public CarListClickHandler(CarInfo car) {
			carInfo = car;
		}

		@Override
		public void onClick(ClickEvent event) {
			currentCar = carInfo;
			displaySingleCarView();
		}
	}

	public abstract void displaySingleCarView();

}
