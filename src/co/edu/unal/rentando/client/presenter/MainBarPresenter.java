package co.edu.unal.rentando.client.presenter;

import co.edu.unal.rentando.client.RentandoServiceAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MainBarPresenter extends Presenter implements IPresenter {

	public interface Display {
		HasClickHandlers getHomeButton();

		HasClickHandlers getLogoutButton();

		HasClickHandlers getProfileButton();

		HasClickHandlers getUsersButton();

		HasClickHandlers getLoginButton();

		HasClickHandlers getExtraInfoButton();

		Widget asWidget();
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
		container.clear();
		container.add(display.asWidget());
	}

	public static enum MenuItemType {
		INICIO, PERFIL, EXTRA, USUARIOS, SALIR, ENTRAR;
	}

	public static enum MenuItemLists {
		normalUser, adminUser, unknown;
	}

	private void bind() {
		if (display.getLoginButton() != null) {
			display.getLoginButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Window.alert("Login");
				}
			});
		}
		if (display.getExtraInfoButton() != null) {
			display.getExtraInfoButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Window.alert("Extra Info");
				}
			});
		}

		if (display.getProfileButton() != null) {
			display.getProfileButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Window.alert("Profile");
				}
			});
		}

		if (display.getLogoutButton() != null) {
			display.getLogoutButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Window.alert("Logout");
				}
			});
		}

		if (display.getHomeButton() != null) {
			display.getHomeButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Window.alert("Home");
				}
			});
		}

		if (display.getUsersButton() != null) {
			display.getUsersButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Window.alert("Users");
				}
			});
		}

	}
}
