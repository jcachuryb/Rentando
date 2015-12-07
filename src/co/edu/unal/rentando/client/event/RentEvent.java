package co.edu.unal.rentando.client.event;

import co.edu.unal.rentando.shared.RentInfo;

import com.google.gwt.event.shared.GwtEvent;

public class RentEvent extends GwtEvent<RentEventHandler> {

	public static Type<RentEventHandler> TYPE = new Type<RentEventHandler>();
	private RentInfo rentInfo;
	
	public RentEvent(RentInfo info) {
		this.rentInfo = info;
	}
	
	@Override
	public Type<RentEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RentEventHandler handler) {
		handler.onRentalAdding(rentInfo);
		handler.onRentalFinalizing(rentInfo);
	}

}
