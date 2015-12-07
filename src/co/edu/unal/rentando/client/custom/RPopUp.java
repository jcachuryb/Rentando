package co.edu.unal.rentando.client.custom;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class RPopUp extends PopupPanel {

	private FlowPanel titleBar = new FlowPanel();
	private String title = "Alerta";
	private FlowPanel frame = new FlowPanel();
	private FlowPanel container = new FlowPanel();

	public RPopUp() {
		joinBar();
	}

	public RPopUp(boolean autoHide) {
		super(autoHide);
		joinBar();
	}

	public RPopUp(boolean autoHide, boolean modal) {
		super(autoHide, modal);
		joinBar();
	}

	public RPopUp(String title) {
		this.title = title;
		joinBar();
	}

	public void joinBar() {
		Label lbl = new Label(title);
		titleBar.getElement().addClassName("popup-bar");
		lbl.getElement().addClassName("popup-bar-title");
		titleBar.add(lbl);
		frame.add(titleBar);
		frame.add(container);
		setWidget(frame);
	}

	public FlowPanel getContainer() {
		return container;
	}

	public void setContainer(FlowPanel container) {
		this.container = container;
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return container;
	}

}
