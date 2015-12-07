package co.edu.unal.rentando.client.presenter;

import co.edu.unal.rentando.client.RentandoServiceAsync;
import co.edu.unal.rentando.client.view.CarListView;
import co.edu.unal.rentando.shared.UserInfo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class AdminCarPresenter extends CarListPresenter implements IPresenter {

	public AdminCarPresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		super(rpcService, eventBus, view.getSuperView());
		display = view;
	}

	Display display;
	public UserInfo userInfo;

	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getDeleteButton();

		CarListView getSuperView();

		void updateCurrentCar();

		Widget asWidget();
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.add(display.asWidget());
	}

	@Override
	public void bind() {
		super.bind();
		display.getDeleteButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				display.updateCurrentCar();
				rpcService.deleteCar(getDisplay().getCurrentcar().getId(),
						new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
								switch (result) {
								case UPD_OK:
									Window.alert("El auto ha sido borrado.");
									getDisplay().transactionDone();
									fetchCars();
									break;
								default:
									getDisplay().transactionDone();
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
				if (getDisplay().getCurrentcar().getId() == null ||getDisplay().getCurrentcar().getId() == "") {
					Window.alert("Ingrese un ID");
					return;
				}
				rpcService.saveCar(getDisplay().getCurrentcar(),
						new AsyncCallback<String>() {

							@Override
							public void onSuccess(final String result) {
								// TODO Auto-generated method stub
								String res;
								switch (result) {
								case UPD_OK:
									res = "El auto ha sido guardado";
									getDisplay().transactionDone();
									fetchCars();
									break;
								case UPD_REPEATED:
									res = "Un auto con esas placas ya existe.";
									break;
								default:
									res = "Ha ocurrido un error. Intente más tarde.";
									break;
								}
								Window.alert(res);

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

	public final static String UPD_OK = "OK";
	public final static String UPD_REPEATED = "REPEATED";
	public final static String UPD_ERROR = "ERROR";

}
