package co.edu.unal.rentando.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditProfileEvent extends GwtEvent<EditProfileEventHandler>{

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<EditProfileEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void dispatch(EditProfileEventHandler handler) {
		handler.onEditProfileInfo(this);
	}

}
