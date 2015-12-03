package co.edu.unal.rentando.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AdminCarConsoleEvent extends GwtEvent<AdminCarConsoleEventHandler> {

	public static Type<AdminCarConsoleEventHandler> TYPE = new Type<AdminCarConsoleEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AdminCarConsoleEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(AdminCarConsoleEventHandler handler) {
		handler.onAdminCarConsole();
	}

}
