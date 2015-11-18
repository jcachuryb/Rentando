package co.edu.unal.rentando.client.presenter;

import co.edu.unal.rentando.client.RentandoServiceAsync;
import co.edu.unal.rentando.client.presenter.MainBarPresenter.MenuItemType;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class IndexPresenter extends Presenter implements IPresenter {
	public interface Display {
		HasClickHandlers getButton();

		Widget asWidget();
	}

	private Display display;
	private MainBarPresenter mbPresenter;

	public IndexPresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		super(rpcService, eventBus);
		display = view;
		mbPresenter = MainBarPresenter.getInstance();
		mbPresenter.setSelected(MenuItemType.INICIO);
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		mbPresenter.go(container);
		container.add(display.asWidget());
	}

	private void bind() {
		display.getButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Works");
				;
			}
		});

	}

}
