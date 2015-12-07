package co.edu.unal.rentando.client.presenter;

import java.util.List;

import co.edu.unal.rentando.client.RentandoServiceAsync;
import co.edu.unal.rentando.client.behavior.ReturnCar;
import co.edu.unal.rentando.shared.RentInfo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class RentAdminPresenter extends Presenter implements IPresenter,ReturnCar {

	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		void setRentalsList(List<RentInfo> list);
		
		void setRentAdmin(ReturnCar admin);
		
		RentInfo getCurrentRent();

		void fillTable();

		Widget asWidget();

	}

	Display display;

	public RentAdminPresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		super(rpcService, eventBus);
		display = view;
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void bind() {
		display.setRentAdmin(this);
		rpcService.fetchRentals(new AsyncCallback<List<RentInfo>>() {
			
			@Override
			public void onSuccess(List<RentInfo> result) {
				display.setRentalsList(result);
				display.fillTable();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}

	@Override
	public Widget getMainWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void returnCar() {
//		Window.alert(display.getCurrentRent().toString());
		
		rpcService.registerReturnedCar(display.getCurrentRent(), new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				Window.alert(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
