package co.edu.unal.rentando.client;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.client.event.IndexEvent;
import co.edu.unal.rentando.client.event.IndexEventHandler;
import co.edu.unal.rentando.client.event.LoginEvent;
import co.edu.unal.rentando.client.event.LoginEventHandler;
import co.edu.unal.rentando.client.event.ViewProfileEvent;
import co.edu.unal.rentando.client.event.ViewProfileEventHandler;
import co.edu.unal.rentando.client.presenter.AdminCarPresenter;
import co.edu.unal.rentando.client.presenter.IPresenter;
import co.edu.unal.rentando.client.presenter.IndexPresenter;
import co.edu.unal.rentando.client.presenter.MainBarPresenter;
import co.edu.unal.rentando.client.presenter.SuperAdminConsolePresenter;
import co.edu.unal.rentando.client.presenter.UserPresenter;
import co.edu.unal.rentando.client.view.AdminCarView;
import co.edu.unal.rentando.client.view.IndexView;
import co.edu.unal.rentando.client.view.MainView;
import co.edu.unal.rentando.client.view.SuperAdminConsoleView;
import co.edu.unal.rentando.client.view.UserView;
import co.edu.unal.rentando.shared.LoginInfo;
import co.edu.unal.rentando.shared.UsrLoginInfo;
import co.edu.unal.rentando.shared.many2many.IUsrLogin.UserRole;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class AppController implements IPresenter, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final RentandoServiceAsync rpcService;
	private HasWidgets container;
	private static List<UserRole> activeRoles = new ArrayList<>();
	private MainBarPresenter mainBarPres;

	public AppController(RentandoServiceAsync rpcService,
			HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
		addActiveRole(UserRole.outside_user);
		MainBarPresenter.initializeBar(rpcService, eventBus, new MainView());
		mainBarPres = MainBarPresenter.getInstance();

	}

	@Override
	public void bind() {
		History.addValueChangeHandler(this);
		eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {

			@Override
			public void onLogin(LoginEvent event) {
				doLogin();
				History.newItem(index);
			}

		});
		eventBus.addHandler(ViewProfileEvent.TYPE,
				new ViewProfileEventHandler() {

					@Override
					public void onViewProfileInfo(ViewProfileEvent event) {
						History.newItem(profile);
					}
				});
		eventBus.addHandler(IndexEvent.TYPE, new IndexEventHandler() {

			@Override
			public void onSuperAdminView() {
				History.newItem(index);
			}
		});

	}

	private void doLogin(List<UserRole> roles) {
		setActiveRoles(roles);
		if (getActiveRoles().size() > 0) {
			History.newItem(index);
		}
		// TODO Figure out what to do next
	}

	@Override
	public void go(HasWidgets container) {
		Window.alert("Main Go");
		this.container = container;
		doLogin();
		loadModules();
	}

	private void loadModules() {
		Window.alert("Load M");

		if (main.equals(History.getToken()) || "".equals(History.getToken())) {
			if (getActiveRoles().contains(UserRole.outside_user)) {
				History.newItem(main);
			} else {
				History.newItem(index);
			}
		} else {
			if (getActiveRoles().contains(UserRole.outside_user)) {
				History.newItem(main);
			} else {
				History.fireCurrentHistoryState();
			}
		}
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		if (token != null) {
			IPresenter presenter = null;
			ArrayList<IPresenter> presenters = new ArrayList<>();
			if (token.equals(main)) {
				presenter = MainBarPresenter.getInstance();
				// presenter = new SuperAdminConsolePresenter(rpcService,
				// eventBus, new SuperAdminConsoleView());
			} else if (token.equals(profile)) {

			} else if (token.equals(index)) {
				List<String> labels = new ArrayList<>();
				String label = "";
				for (UserRole roles : getActiveRoles()) {
					switch (roles) {
					case normal_user:
						// presenters.add(new UserPresenter(
						// rpcService, eventBus,
						// new UserView()));
						presenters.add(new SuperAdminConsolePresenter(
								rpcService, eventBus,
								new SuperAdminConsoleView()));
						label = "Usuario";
						break;
					case admin_user:
						presenters.add(new AdminCarPresenter(rpcService,
								eventBus, new AdminCarView()));
						label = "Admin";
						break;
					case super_admin:
						presenters.add(new SuperAdminConsolePresenter(
								rpcService, eventBus,
								new SuperAdminConsoleView()));
						label = "Súper Admin";
						break;
					default:
						Window.alert("No role");
						break;
					}
					labels.add(label);
				}

				presenter = new IndexPresenter(rpcService, eventBus,
						new IndexView(), presenters, labels);

			} else if (token.equals(userlist)) {
				presenter = new SuperAdminConsolePresenter(
						rpcService, eventBus,
						new SuperAdminConsoleView());
			} else if (token.equals(admin)) {
				presenter = new AdminCarPresenter(rpcService, eventBus,
						new AdminCarView());
			}

			if (presenter != null) {
				presenter.go(container);
			} else {
				Window.alert("NULLLL");
			}
		}
	}

	public static List<UserRole> getActiveRoles() {
		return activeRoles;
	}

	public static void addActiveRole(UserRole roles) {
		activeRoles.add(roles);
	}

	public static void clearActiveRoles() {
		activeRoles.clear();
	}

	public static void setActiveRoles(List<UserRole> roles) {
		clearActiveRoles();
		activeRoles = roles;
	}

	public final static String login = "login";
	public final static String index = "index";
	public final static String admin = "admin";
	public final static String profile = "profile";
	public final static String main = "main";
	public final static String userlist = "userlist";
	public final static String logout = "logout";
	public final static String superadmin = "superadmin";

	@Override
	public Widget getMainWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	private static final Auth AUTH = Auth.get();
	private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
	private static final String GOOGLE_CLIENT_ID = "948749463453-ghhqlbkdfr27n3litcsrunbvbitvcckb.apps.googleusercontent.com";
	private static final String PLUS_ME_EMAIL = "https://www.googleapis.com/auth/userinfo.email";

	private void doLogin() {
		// rpcService.login(GWT.getHostPageBaseURL(),
		// new AsyncCallback<LoginInfo>() {
		// @Override
		// public void onFailure(final Throwable caught) {
		// GWT.log("login -> onFailure");
		// }
		//
		// @Override
		// public void onSuccess(final LoginInfo result) {
		// Window.alert(result.toString());
		// if (result.getName() != null
		// && !result.getName().isEmpty()) {
		// mainBarPres.loadLogout(result);
		// addGoogleAuthHelper();
		// } else {
		// mainBarPres.loadLogin(result);
		// loadModules();
		// }
		// }
		// });

		setActiveRoles(getTestRoles());
		Window.alert("TEST ROLES");

	}

	// TODO #07: add helper methods for Login, Logout and AuthRequest

	private void addGoogleAuthHelper() {
		final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL,
				GOOGLE_CLIENT_ID).withScopes(PLUS_ME_EMAIL);

		AUTH.login(req, new Callback<String, Throwable>() {
			@Override
			public void onSuccess(final String token) {
				if (!token.isEmpty()) {
					rpcService.loginDetails(token,
							new AsyncCallback<UsrLoginInfo>() {

								@Override
								public void onSuccess(UsrLoginInfo result) {

									Window.alert("Usuario cargado:"
											+ result.toString());
									AppController.setActiveRoles(result
											.getRoles());

									loadModules();

								}

								@Override
								public void onFailure(Throwable caught) {
									Window.alert(caught.toString());
									List<UserRole> roles = new ArrayList<UserRole>();
									roles.add(UserRole.outside_user);
									AppController.setActiveRoles(roles);

									loadModules();
								}
							});
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

	public List<UserRole> getTestRoles() {
		ArrayList<UserRole> roles = new ArrayList<UserRole>();
		roles.add(UserRole.normal_user);
		roles.add(UserRole.admin_user);
		roles.add(UserRole.super_admin);
		return roles;
	}

}
