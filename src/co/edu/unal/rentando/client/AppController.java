package co.edu.unal.rentando.client;

import co.edu.unal.rentando.client.event.LoginEvent;
import co.edu.unal.rentando.client.event.LoginEventHandler;
import co.edu.unal.rentando.client.presenter.IPresenter;
import co.edu.unal.rentando.client.presenter.IndexPresenter;
import co.edu.unal.rentando.client.presenter.LoginPresenter;
import co.edu.unal.rentando.client.view.IndexView;
import co.edu.unal.rentando.client.view.LoginView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
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
	}

	private void bind() {
		History.addValueChangeHandler(this);
		eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {

			@Override
			public void onLogin(LoginEvent event) {

			}
		});

	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;
		if ("".equals(History.getToken())) {
			History.newItem("index");
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
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}
}
