package co.edu.unal.rentando.client.presenter;

import java.util.List;

import co.edu.unal.rentando.client.RentandoServiceAsync;
import co.edu.unal.rentando.shared.UsrLoginInfo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class SuperAdminConsolePresenter extends Presenter implements IPresenter {

	public interface Display {

		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		
		void setLoginList(List<UsrLoginInfo> loginList);
		
		List<UsrLoginInfo> getListOfChanges();
		
		UsrLoginInfo getNewLogin();
		
		void fillTable();
		
		Widget asWidget();
	}

	Display display;

	public SuperAdminConsolePresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		super(rpcService, eventBus);
		display = view;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void go(HasWidgets container) {
		container.add(display.asWidget());
		bind();
	}

	@Override
	public void bind() {
		display.getSaveButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rpcService.saveLoginInfoBatch(display.getListOfChanges(), new AsyncCallback<String>() {
					
					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						Window.alert(result);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		});
		display.getCancelButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			}
		});
		rpcService.fetchAllLogins(new AsyncCallback<List<UsrLoginInfo>>() {
			
			@Override
			public void onSuccess(List<UsrLoginInfo> result) {
				display.setLoginList(result);
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
		return display.asWidget();
	}

}
