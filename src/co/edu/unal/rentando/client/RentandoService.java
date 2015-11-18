package co.edu.unal.rentando.client;

import co.edu.unal.rentando.shared.LoginInfo;
import co.edu.unal.rentando.shared.UserInfo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface RentandoService extends RemoteService {
	
	// TODO #09: start create login helper methods in service interface
		String getUserEmail(String token);

		LoginInfo login(String requestUri);

		UserInfo loginDetails(String token);

		// TODO #09:> end
}
