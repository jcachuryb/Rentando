package co.edu.unal.rentando.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SuperAdminEvent extends GwtEvent<SuperAdminEventHandler> {

	public static Type<SuperAdminEventHandler> TYPE = new Type<SuperAdminEventHandler>();

	@Override
	public Type<SuperAdminEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(SuperAdminEventHandler handler) {
		handler.onSuperAdminView();
	}

}
