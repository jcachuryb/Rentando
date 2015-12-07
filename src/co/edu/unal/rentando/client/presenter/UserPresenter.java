package co.edu.unal.rentando.client.presenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.unal.rentando.client.AppController;
import co.edu.unal.rentando.client.RentandoServiceAsync;
import co.edu.unal.rentando.client.behavior.LoadCarRentals;
import co.edu.unal.rentando.client.event.RentEvent;
import co.edu.unal.rentando.client.view.CarListView;
import co.edu.unal.rentando.shared.CarInfo;
import co.edu.unal.rentando.shared.RentInfo;
import co.edu.unal.rentando.shared.RentalDate;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class UserPresenter extends CarListPresenter implements IPresenter,
		LoadCarRentals {
	public interface Display {
		void setRentInfo(RentInfo rent);

		RentInfo getRenInfo();

		HasClickHandlers getRentButton();

		void loadCurrentCarRentals(List<RentInfo> list);

		void setRentLoader(LoadCarRentals loader);

		CarListView getSuperView();

		Widget asWidget();
	}

	Display display;

	public UserPresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		super(rpcService, eventBus, view.getSuperView());
		display = view;
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.add(display.asWidget());
	}

	@Override
	public void bind() {
		super.bind();
		display.setRentLoader(this);
		display.getRentButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new RentEvent(display.getRenInfo()));
				getDisplay().transactionDone();
			}
		});

		loadUserRental();
	}

	@Override
	public Widget getMainWidget() {
		// TODO Auto-generated method stub
		return display.asWidget();
	}

	@Override
	public List<RentInfo> loadRentals(String id) {
		rpcService.fetchCarAssocRents(id, new AsyncCallback<List<RentInfo>>() {

			@Override
			public void onSuccess(List<RentInfo> result) {
				display.loadCurrentCarRentals(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
		return new ArrayList<RentInfo>();
	}

	@Override
	public void loadUserRental() {
		rpcService.loadRent(AppController.getUserid(),
				new AsyncCallback<RentInfo>() {

					@Override
					public void onSuccess(RentInfo result) {
						display.setRentInfo(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});
	}

}
