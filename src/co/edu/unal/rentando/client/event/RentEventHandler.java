package co.edu.unal.rentando.client.event;

import co.edu.unal.rentando.shared.RentInfo;

import com.google.gwt.event.shared.EventHandler;

public interface RentEventHandler extends EventHandler {

	void onRentalAdding(RentInfo info);

	void onRentalFinalizing(RentInfo info);
}
