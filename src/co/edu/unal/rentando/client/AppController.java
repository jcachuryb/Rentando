package co.edu.unal.rentando.client;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.client.behavior.RoleChange;
import co.edu.unal.rentando.client.event.AdminCarConsoleEvent;
import co.edu.unal.rentando.client.event.AdminCarConsoleEventHandler;
import co.edu.unal.rentando.client.event.LoginEvent;
import co.edu.unal.rentando.client.event.LoginEventHandler;
import co.edu.unal.rentando.client.event.RentEvent;
import co.edu.unal.rentando.client.event.RentEventHandler;
import co.edu.unal.rentando.client.event.SuperAdminEvent;
import co.edu.unal.rentando.client.event.SuperAdminEventHandler;
import co.edu.unal.rentando.client.event.UserViewEvent;
import co.edu.unal.rentando.client.event.UserViewEventHandler;
import co.edu.unal.rentando.client.event.ViewProfileEvent;
import co.edu.unal.rentando.client.event.ViewProfileEventHandler;
import co.edu.unal.rentando.client.event.ViewUsersEvent;
import co.edu.unal.rentando.client.event.ViewUsersEventHandler;
import co.edu.unal.rentando.client.presenter.AdminCarPresenter;
import co.edu.unal.rentando.client.presenter.CarListPresenter;
import co.edu.unal.rentando.client.presenter.IPresenter;
import co.edu.unal.rentando.client.presenter.MainBarPresenter;
import co.edu.unal.rentando.client.presenter.RentAdminPresenter;
import co.edu.unal.rentando.client.presenter.SuperAdminConsolePresenter;
import co.edu.unal.rentando.client.presenter.UserPresenter;
import co.edu.unal.rentando.client.view.AdminCarView;
import co.edu.unal.rentando.client.view.MainView;
import co.edu.unal.rentando.client.view.RentAdminView;
import co.edu.unal.rentando.client.view.SuperAdminConsoleView;
import co.edu.unal.rentando.client.view.UserView;
import co.edu.unal.rentando.shared.RentInfo;
import co.edu.unal.rentando.shared.UsrLoginInfo;
import co.edu.unal.rentando.shared.LoginInfo;
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
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class AppController implements IPresenter, ValueChangeHandler<String> {
	private ArrayList<RoleChange> rolesObs;
	private final HandlerManager eventBus;
	private final RentandoServiceAsync rpcService;
	private static List<UserRole> activeRoles = new ArrayList<>();
	private static String userid = "";
	private MainBarPresenter mainBarPres;
	ScrollPanel content;

	public AppController(RentandoServiceAsync rpcService,
			HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;

		bind();
		addActiveRole(UserRole.outside_user);

		notifyRoleChanges();

		content = new ScrollPanel();
		content.getElement().addClassName("container");
		content.setHeight("100%");

	}

	@Override
	public void bind() {
		History.addValueChangeHandler(this);
		rolesObs = new ArrayList<RoleChange>();
		MainView mainBarView = new MainView();
		MainBarPresenter.initializeBar(rpcService, eventBus, mainBarView);
		mainBarPres = MainBarPresenter.getInstance();
		rolesObs.add(mainBarView);
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
		eventBus.addHandler(SuperAdminEvent.TYPE, new SuperAdminEventHandler() {

			@Override
			public void onSuperAdminView() {
				History.newItem(superadmin);
			}
		});
		eventBus.addHandler(AdminCarConsoleEvent.TYPE,
				new AdminCarConsoleEventHandler() {

					@Override
					public void onAdminCarConsole() {
						History.newItem(admin);
					}
				});
		eventBus.addHandler(ViewUsersEvent.TYPE, new ViewUsersEventHandler() {

			@Override
			public void onViewUsers() {
				History.newItem(userlist);
			}
		});

		eventBus.addHandler(UserViewEvent.TYPE, new UserViewEventHandler() {

			@Override
			public void onViewUserInterface() {
				History.newItem(index);
			}
		});

		eventBus.addHandler(RentEvent.TYPE, new RentEventHandler() {

			@Override
			public void onRentalFinalizing(RentInfo info) {

			}

			@Override
			public void onRentalAdding(final RentInfo info) {
				if (getUserid() != "") {
					info.setId(getUserid());
					rpcService.saveRent(info, new AsyncCallback<String>() {

						@Override
						public void onSuccess(String result) {
							Window.alert(result);

						}

						@Override
						public void onFailure(Throwable caught) {

						}
					});
				}else{
					Window.alert("Not logged in");
				}
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		container.add(mainBarPres.getMainWidget());
		container.add(content);
		doLogin();
		// loadModules();
	}

	private void loadModules() {

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
			if (token.equals(main)) {

			} else if (token.equals(index)) {
				presenter = new UserPresenter(rpcService, eventBus,
						new UserView());
			} else if (token.equals(profile)) {

			} else if (token.equals(admin)) {
				presenter = new AdminCarPresenter(rpcService, eventBus,
						new AdminCarView());
			} else if (token.equals(userlist)) {
				presenter = new RentAdminPresenter(rpcService, eventBus, new RentAdminView());
			} else if (token.equals(superadmin)) {
				presenter = new SuperAdminConsolePresenter(rpcService,
						eventBus, new SuperAdminConsoleView());
			}

			if (presenter != null && !presenter.equals(mainBarPres)) {
				content.clear();
				presenter.go(content);
			} else {
				// Window.alert("NULLLL");
			}
		}
	}

	public List<UserRole> getActiveRoles() {
		return activeRoles;
	}

	public void addActiveRole(UserRole roles) {
		activeRoles.add(roles);
		notifyRoleChanges();
	}

	public static void clearActiveRoles() {
		activeRoles.clear();
	}

	public void setActiveRoles(List<UserRole> roles) {
		clearActiveRoles();
		activeRoles = roles;
		notifyRoleChanges();
	}

	public void notifyRoleChanges() {
		for (RoleChange observer : rolesObs) {
			observer.onRoleChange(activeRoles);
		}
	}

	public static void notifyCurrentUser() {

	}

	public static String getUserid() {
		return userid;
	}

	public static void setUserid(String userid) {
		AppController.userid = userid;
		notifyCurrentUser();
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
							mainBarPres.loadLogout(result);
							addGoogleAuthHelper();
						} else {
							mainBarPres.loadLogin(result);
							loadModules();
						}
					}
				});

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
									setActiveRoles(result.getRoles());
									setUserid(result.getId());
									loadModules();
								}

								@Override
								public void onFailure(Throwable caught) {
									List<UserRole> roles = new ArrayList<UserRole>();
									roles.add(UserRole.outside_user);
									setUserid("");
									setActiveRoles(roles);

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
