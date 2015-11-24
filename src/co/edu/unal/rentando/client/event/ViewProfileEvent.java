package co.edu.unal.rentando.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ViewProfileEvent extends GwtEvent<ViewProfileEventHandler> {

	public static Type<ViewProfileEventHandler> TYPE = new Type<ViewProfileEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ViewProfileEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ViewProfileEventHandler handler) {
		handler.onViewProfileInfo(this);
	}

}
