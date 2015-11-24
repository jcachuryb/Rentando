package co.edu.unal.rentando.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ViewUsersEvent extends GwtEvent<ViewUsersEventHandler> {

	public static Type<ViewUsersEventHandler> TYPE = new Type<ViewUsersEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ViewUsersEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(ViewUsersEventHandler handler) {
		handler.onViewUsers();
	}
	
	
}
