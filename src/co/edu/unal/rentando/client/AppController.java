package co.edu.unal.rentando.client;

import java.util.ArrayList;

import co.edu.unal.rentando.client.event.LoginEvent;
import co.edu.unal.rentando.client.event.LoginEventHandler;
import co.edu.unal.rentando.client.presenter.AdminCarPresenter;
import co.edu.unal.rentando.client.presenter.IPresenter;
import co.edu.unal.rentando.client.presenter.IndexPresenter;
import co.edu.unal.rentando.client.presenter.LoginPresenter;
import co.edu.unal.rentando.client.presenter.MainBarPresenter;
import co.edu.unal.rentando.client.presenter.MainBarPresenter.MenuItemLists;
import co.edu.unal.rentando.client.view.AdminCarView;
import co.edu.unal.rentando.client.view.IndexView;
import co.edu.unal.rentando.client.view.LoginView;
import co.edu.unal.rentando.client.view.MainView;
import co.edu.unal.rentando.shared.many2many.IUser.UserRole;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements IPresenter, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final RentandoServiceAsync rpcService;
	private HasWidgets container;

	public AppController(RentandoServiceAsync rpcService,
			HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
		MainBarPresenter.initializeBar(rpcService, eventBus, new MainView(MenuItemLists.adminUser));
	}

	private void bind() {
		History.addValueChangeHandler(this);
		eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {

			@Override
			public void onLogin(LoginEvent event) {
				doLogin(event.getRole());
			}

		});

	}

	private void doLogin(UserRole role) {
		
		switch (role) {
		case admin_user:
			History.newItem("admin");
			Window.alert("admin user");
			break;
		case normal_user:
			History.newItem("user");
			Window.alert("normal user");
			break;
		default:
			Window.alert("What the fuck?");
			History.newItem("index");
			break;
		}
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;
		if ("".equals(History.getToken())) {
			History.newItem("admin");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			IPresenter presenter = null;

			if (token.equals("login")) {
				presenter = new LoginPresenter(rpcService, eventBus,
						new LoginView());
			} else if (token.equals("index")) {
				presenter = new IndexPresenter(rpcService, eventBus,
						new IndexView());
			}else if (token.equals("admin")) {
				presenter = new AdminCarPresenter(rpcService, eventBus, new AdminCarView());
			}else if (token.equals("user")) {
				presenter = new IndexPresenter(rpcService, eventBus,
						new IndexView());
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}
}
