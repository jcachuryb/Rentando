package co.edu.unal.rentando.client.presenter;

import co.edu.unal.rentando.client.RentandoServiceAsync;

import com.google.gwt.event.shared.HandlerManager;

public class Presenter {
	protected final RentandoServiceAsync rpcService;
	protected final HandlerManager eventBus;

	public Presenter(RentandoServiceAsync rpcService, HandlerManager eventBus) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
	}

}
