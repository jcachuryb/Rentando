package co.edu.unal.rentando.client.presenter;

import java.util.List;

import co.edu.unal.rentando.client.RentandoServiceAsync;
import co.edu.unal.rentando.shared.CarInfo;
import co.edu.unal.rentando.shared.RentInfo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class UserPresenter extends Presenter implements IPresenter {
	public interface Display {
		void setRentInfo(RentInfo rent);

		void setCarList(List<CarInfo> list);

		RentInfo getRenInfo();

		HasClickHandlers getRentButton();

		Widget asWidget();
	}

	Display display;

	public UserPresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		super(rpcService, eventBus);
		display = view;
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.add(display.asWidget());
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		display.getRentButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("New Rent");

			}
		});
	}

	@Override
	public Widget getMainWidget() {
		// TODO Auto-generated method stub
		return display.asWidget();
	}

}
