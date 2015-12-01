package co.edu.unal.rentando.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Rentando implements EntryPoint {

	FlowPanel mainPanel;

	@Override
	public void onModuleLoad() {
		RentandoServiceAsync rpcService = GWT.create(RentandoService.class);
		HandlerManager eventBus = new HandlerManager(null);
		AppController appViewer = new AppController(rpcService, eventBus);
		mainPanel = new FlowPanel();
		mainPanel.getElement().addClassName("the-whole-thing");
		RootLayoutPanel.get().add(mainPanel);
		appViewer.go(mainPanel);
	}

}