package co.edu.unal.rentando.client.presenter;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.client.RentandoServiceAsync;
import co.edu.unal.rentando.client.presenter.MainBarPresenter.MenuItemLists;
import co.edu.unal.rentando.client.presenter.MainBarPresenter.MenuItemType;
import co.edu.unal.rentando.client.view.MainView;
import co.edu.unal.rentando.shared.CarInfo;
import co.edu.unal.rentando.shared.UserInfo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class AdminCarPresenter extends Presenter implements IPresenter {

	public AdminCarPresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		super(rpcService, eventBus);
		display = view;
		mainBarPresenter = MainBarPresenter.getInstance();
	}

	MainBarPresenter mainBarPresenter;
	Display display;
	public UserInfo userInfo;
	
	public interface Display{
		HasClickHandlers getSaveButton();
		HasClickHandlers getDeleteButton();
		void fillCarList(List<CarInfo> list);
		CarInfo getCurrentcar();
		Widget asWidget();
	}

	
	private void bind() {
		display.getDeleteButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("delete car");
			}
		});
		display.getSaveButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("save car");
			}
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		mainBarPresenter.go(container);
		mainBarPresenter.setSelected(MenuItemType.INICIO);
		display.fillCarList(fetchCars());
		container.add(display.asWidget());
		bind();
		
	}
	
	public List<CarInfo> fetchCars(){
		return new ArrayList<>();
	}
}
