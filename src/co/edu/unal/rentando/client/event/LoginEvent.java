package co.edu.unal.rentando.client.event;

import java.util.List;

import co.edu.unal.rentando.shared.many2many.IUsrLogin.UserRole;

import com.google.gwt.event.shared.GwtEvent;

public class LoginEvent extends GwtEvent<LoginEventHandler>{
	public static Type<LoginEventHandler> TYPE = new Type<LoginEventHandler>();
	
	
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoginEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(LoginEventHandler handler) {
		handler.onLogin(this);
	}

}
