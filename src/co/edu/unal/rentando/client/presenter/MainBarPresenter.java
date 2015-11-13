package co.edu.unal.rentando.client.presenter;

import co.edu.unal.rentando.client.RentandoServiceAsync;
import co.edu.unal.rentando.client.event.LoginEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class MainBarPresenter extends Presenter implements IPresenter {

	public interface Display {
		HasClickHandlers getHomeButton();

		HasClickHandlers getLogoutButton();

		HasClickHandlers getProfileButton();

		HasClickHandlers getUsersButton();

		HasClickHandlers getLoginButton();

		HasClickHandlers getExtraInfoButton();
	}

	Display display;

	public MainBarPresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		super(rpcService, eventBus);
		this.display = view;
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		// TODO Auto-generated method stub

	}

	private void bind() {
		display.getLoginButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LoginEvent());

			}
		});

	}
}
