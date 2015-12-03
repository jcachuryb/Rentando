package co.edu.unal.rentando.client.presenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.edu.unal.rentando.client.RentandoServiceAsync;
import co.edu.unal.rentando.client.view.CarListView;
import co.edu.unal.rentando.shared.CarInfo;
import co.edu.unal.rentando.shared.RentInfo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class UserPresenter extends CarListPresenter implements IPresenter {
	public interface Display {
		void setRentInfo(RentInfo rent);

		RentInfo getRenInfo();

		HasClickHandlers getRentButton();

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

		display.getRentButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Date due = new Date();
				// SimpleDateFormat format = new SimpleDateFormat("");
				RentInfo rent = new RentInfo();
				rent.setId("truancamilo@gmail.com");
				rent.setInitDate(new Date());
				rent.setDueDate(due);
				rent.setCar(getDisplay().getCurrentcar());
				rpcService.saveRent(rent, new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						Window.alert(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});
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
