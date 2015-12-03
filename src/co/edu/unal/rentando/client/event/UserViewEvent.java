package co.edu.unal.rentando.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UserViewEvent extends GwtEvent<UserViewEventHandler> {

	public static Type<UserViewEventHandler> TYPE = new Type<UserViewEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(UserViewEventHandler handler) {
		handler.onViewUserInterface();
	}

}
