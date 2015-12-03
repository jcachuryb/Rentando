package co.edu.unal.rentando.client.presenter;

import java.util.List;

import co.edu.unal.rentando.client.RentandoServiceAsync;
import co.edu.unal.rentando.shared.CarInfo;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public abstract class CarListPresenter extends Presenter implements IPresenter {

	public interface Display {
		void fillCarList(List<CarInfo> list);

		CarInfo getCurrentcar();

		void transactionDone();

		Widget asWidget();
	}

	public CarListPresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		super(rpcService, eventBus);
		display = view;
	}

	@Override
	public abstract void go(HasWidgets container) ;

	@Override
	public void bind() {
		fetchCars();
	}

	@Override
	public Widget getMainWidget() {
		// TODO Auto-generated method stub
		return null;
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

	public Display getDisplay() {
		return display;
	}
	
	private Display display;

}
