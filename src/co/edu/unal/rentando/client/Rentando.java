package co.edu.unal.rentando.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Rentando implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RentandoServiceAsync rpcService = GWT.create(RentandoService.class);
	    HandlerManager eventBus = new HandlerManager(null);
	    AppController appViewer = new AppController(rpcService, eventBus);
	    appViewer.go(RootPanel.get());
	}


}