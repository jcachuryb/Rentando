package co.edu.unal.rentando.client.presenter;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.client.RentandoServiceAsync;
import co.edu.unal.rentando.client.event.ViewProfileEvent;
import co.edu.unal.rentando.shared.CarInfo;
import co.edu.unal.rentando.shared.UserInfo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class AdminCarPresenter extends Presenter implements IPresenter {

	public AdminCarPresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		super(rpcService, eventBus);
		display = view;
	}

	Display display;
	public UserInfo userInfo;

	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getDeleteButton();

		void fillCarList(List<CarInfo> list);

		CarInfo getCurrentcar();

		void updateCurrentCar();

		void transactionDone();

		Widget asWidget();
	}

	@Override
	public void go(HasWidgets container) {
		fetchCars();
		bind();
		container.add(display.asWidget());
	}

	@Override
	public void bind() {
		display.getDeleteButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				display.updateCurrentCar();
				rpcService.deleteCar(display.getCurrentcar().getId(),
						new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
								switch (result) {
								case UPD_OK:
									Window.alert("El auto ha sido borrado.");
									display.transactionDone();
									fetchCars();
									break;
								default:
									display.transactionDone();
									break;
								}
								
							}

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}
						});
			}
		});
		display.getSaveButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				display.updateCurrentCar();
				rpcService.saveCar(display.getCurrentcar(),
						new AsyncCallback<String>() {

							@Override
							public void onSuccess(final String result) {
								// TODO Auto-generated method stub
								String res;
								switch (result) {
								case UPD_OK:
									res = "El auto ha sido guardado";
									display.transactionDone();
									eventBus.fireEvent(new ViewProfileEvent());
									break;
								case UPD_REPEATED:
									res = "Un auto con esas placas ya existe.";
									break;
								default:
									res = "Ha ocurrido un error. Intente más tarde.";
									break;
								}
								Window.alert(res);
								fetchCars();
								
							}

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

								Window.alert("Crap: " + caught);

							}
						});
			}
		});

		

	}

	@Override
	public Widget getMainWidget() {
		// TODO Auto-generated method stub
		return display.asWidget();
	}

	public void fetchCars() {
		rpcService.fetchCars(new AsyncCallback<List<CarInfo>>() {

			@Override
			public void onSuccess(List<CarInfo> result) {
				display.fillCarList(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Something went wrong fetching the cars.");
			}
		});
	}

	public final static String UPD_OK = "OK";
	public final static String UPD_REPEATED = "REPEATED";
	public final static String UPD_ERROR = "ERROR";

}
