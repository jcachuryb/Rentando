package co.edu.unal.rentando.client;

import co.edu.unal.rentando.shared.LoginInfo;
import co.edu.unal.rentando.shared.UserInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface RentandoServiceAsync {

	// TODO #10: create login helper methods in service asynchronous interface
	void getUserEmail(String token, AsyncCallback<String> callback);

	void login(String requestUri, AsyncCallback<LoginInfo> asyncCallback);

	void loginDetails(String token, AsyncCallback<UserInfo> asyncCallback);
	// TODO #10:> end
}
