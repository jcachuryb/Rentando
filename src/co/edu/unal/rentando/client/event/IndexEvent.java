package co.edu.unal.rentando.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class IndexEvent extends GwtEvent<IndexEventHandler> {

	public static Type<IndexEventHandler> TYPE = new Type<IndexEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<IndexEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(IndexEventHandler handler) {
		handler.onSuperAdminView();
	}

}
