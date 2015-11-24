package co.edu.unal.rentando.client.presenter;

import java.util.List;

import co.edu.unal.rentando.client.AppController;
import co.edu.unal.rentando.client.RentandoServiceAsync;
import co.edu.unal.rentando.client.event.IndexEvent;
import co.edu.unal.rentando.client.event.LoginEvent;
import co.edu.unal.rentando.shared.LoginInfo;
import co.edu.unal.rentando.shared.UsrLoginInfo;
import co.edu.unal.rentando.shared.many2many.IUsrLogin.UserRole;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MainBarPresenter extends Presenter implements IPresenter {

	public interface Display {
		HasClickHandlers getHomeButton();

		HasClickHandlers getLogoutButton();

		HasClickHandlers getProfileButton();

		HasClickHandlers getUsersButton();

		HasClickHandlers getLoginButton();

		void setSelected(MenuItemType item);

		Anchor getLoginLink();

		Anchor getLogoutLink();

		void updateMenuBarList(List<UserRole> roles);

		Widget asWidget();
	}

	private static MainBarPresenter instance;

	private Display display;

	private MainBarPresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		super(rpcService, eventBus);
		this.display = view;
		bind();
	}

	public static void initializeBar(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		instance = new MainBarPresenter(rpcService, eventBus, view);
	}

	public static MainBarPresenter getInstance() {
		instance.display.updateMenuBarList(AppController.getActiveRoles());
		return instance;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public Widget getMainWidget() {
		// TODO Auto-generated method stub
		return display.asWidget();
	}

	public static enum MenuItemType {
		INICIO, PERFIL, USUARIOS, SALIR, ENTRAR, ADMIN;
	}

	public static enum MenuItemLists {
		normalUser, adminUser, unknown;
	}

	public synchronized void setSelected(MenuItemType item) {
		display.setSelected(item);
	}

	public void loadLogin(final LoginInfo loginInfo) {
		display.getLoginLink().setHref(loginInfo.getLoginUrl());
		display.getLoginLink().setText("Sign in");
		display.getLoginLink().setEnabled(true);
	}

	public void loadLogout(final LoginInfo loginInfo) {
		display.getLogoutLink().setHref(loginInfo.getLogoutUrl());
		display.getLogoutLink().setText("Sign out");
	}

	@Override
	public void bind() {
		if (display.getLoginButton() != null) {
			display.getLoginLink().setEnabled(false);
			display.getLoginButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					// Window.alert("sdfdsfadsf");
					UsrLoginInfo uli = UsrLoginInfo.getTestLogin();

					display.updateMenuBarList(uli.getRoles());
					eventBus.fireEvent(new LoginEvent(uli.getRoles()));
				}
			});
		}

		if (display.getProfileButton() != null) {
			display.getProfileButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					display.setSelected(MenuItemType.PERFIL);
					Window.alert("Profile");
				}
			});
		}

		if (display.getLogoutButton() != null) {
			display.getLogoutButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					display.setSelected(MenuItemType.SALIR);
					Window.alert("Logout");
				}
			});
		}

		if (display.getHomeButton() != null) {
			display.getHomeButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					display.setSelected(MenuItemType.INICIO);
					eventBus.fireEvent(new IndexEvent());
				}
			});
		}

		if (display.getUsersButton() != null) {
			display.getUsersButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					display.setSelected(MenuItemType.USUARIOS);
					Window.alert("Users");
				}
			});
		}
	}
}
