package co.edu.unal.rentando.client.presenter;

import co.edu.unal.rentando.client.RentandoServiceAsync;
import co.edu.unal.rentando.client.event.LoginEvent;
import co.edu.unal.rentando.shared.LoginInfo;
import co.edu.unal.rentando.shared.UserInfo;
import co.edu.unal.rentando.shared.many2many.IUser.UserRole;

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
import com.google.gwt.view.client.SetSelectionModel;

public class MainBarPresenter extends Presenter implements IPresenter {

	public interface Display {
		HasClickHandlers getHomeButton();

		HasClickHandlers getLogoutButton();

		HasClickHandlers getProfileButton();

		HasClickHandlers getUsersButton();

		HasClickHandlers getLoginButton();

		HasClickHandlers getExtraInfoButton();

		boolean doLogin();
		
		void setSelected(MenuItemType item);

		Anchor getLoginLink();

		Anchor getLogoutLink();

		Widget asWidget();
	}

	private static MainBarPresenter instance;

	private static final Auth AUTH = Auth.get();
	private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
	private static final String GOOGLE_CLIENT_ID = "948749463453-ghhqlbkdfr27n3litcsrunbvbitvcckb.apps.googleusercontent.com";
	private static final String PLUS_ME_PROFILE = "https://www.googleapis.com/auth/userinfo.profile";
	private static final String PLUS_ME_EMAIL = "https://www.googleapis.com/auth/userinfo.email";
	private Display display;

	private MainBarPresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		super(rpcService, eventBus);
		this.display = view;
	}

	public static void initializeBar(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view){
		instance = new MainBarPresenter(rpcService, eventBus, view);
	}
	
	
	public static MainBarPresenter getInstance() {
		return instance;
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

	}

	private void doLogin() {
		rpcService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {
					@Override
					public void onFailure(final Throwable caught) {
						GWT.log("login -> onFailure");
					}

					@Override
					public void onSuccess(final LoginInfo result) {
						if (result.getName() != null
								&& !result.getName().isEmpty()) {
							addGoogleAuthHelper();
							loadLogout(result);
						} else {
							loadLogin(result);
						}
					}
				});
	}

	public static enum MenuItemType {
		INICIO, PERFIL, EXTRA, USUARIOS, SALIR, ENTRAR;
	}

	public static enum MenuItemLists {
		normalUser, adminUser, unknown;
	}

	public void setSelected(MenuItemType item){
		display.setSelected(item);
	}
	
	// TODO #07: add helper methods for Login, Logout and AuthRequest

	private void addGoogleAuthHelper() {
		final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL,
				GOOGLE_CLIENT_ID).withScopes(PLUS_ME_PROFILE, PLUS_ME_EMAIL);

		AUTH.login(req, new Callback<String, Throwable>() {
			@Override
			public void onSuccess(final String token) {
				if (!token.isEmpty()) {
					// TODO: Validate if user exists first.
					UserInfo u = UserInfo.getTestUser();
					eventBus.fireEvent(new LoginEvent(u.getRole()));
				}
			}

			@Override
			public void onFailure(final Throwable caught) {
				Window.alert("Ocurrió un error. Vuelve a intentarlo");
				GWT.log("Error -> loginDetails\n" + caught.getMessage());
			}
		});
	}

	// TODO #07:> end

	private void loadLogin(final LoginInfo loginInfo) {
		display.getLoginLink().setHref(loginInfo.getLoginUrl());
		display.getLoginLink().setText("Sign in");
	}

	private void loadLogout(final LoginInfo loginInfo) {
		display.getLogoutLink().setHref(loginInfo.getLogoutUrl());
		display.getLogoutLink().setText("Sign out");
	}

	private void bind() {
		if (display.getLoginButton() != null) {

			display.getLoginButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					display.setSelected(MenuItemType.ENTRAR);
				}
			});
		}
		if (display.getExtraInfoButton() != null) {
			display.getExtraInfoButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					display.setSelected(MenuItemType.EXTRA);
					Window.alert("Extra Info");
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
					Window.alert("Home");
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

		if (display.doLogin()) {
			doLogin();
		}

	}
}
