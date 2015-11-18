package co.edu.unal.rentando.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import co.edu.unal.rentando.client.RentandoService;
import co.edu.unal.rentando.shared.LoginInfo;
import co.edu.unal.rentando.shared.UserInfo;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RentandoServiceImpl extends RemoteServiceServlet implements
		RentandoService {

	// TODO #11: implement login helper methods in service implementation

		@Override
		public String getUserEmail(final String token) {
			final UserService userService = UserServiceFactory.getUserService();
			final User user = userService.getCurrentUser();
			if (null != user) {
				return user.getEmail();
			} else {
				return "noreply@sample.com";
			}
		}

		@Override
		public LoginInfo login(final String requestUri) {
			final UserService userService = UserServiceFactory.getUserService();
			final User user = userService.getCurrentUser();
			final LoginInfo loginInfo = new LoginInfo();
			if (user != null) {
				System.out.println("Logout");
				loginInfo.setLoggedIn(true);
				loginInfo.setName(user.getEmail());
				loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
			} else {
				loginInfo.setLoggedIn(false);
				loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
				System.out.println("Login");
			}
			return loginInfo;
		}

		@Override
		public UserInfo loginDetails(final String token) {
			String url = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token="
					+ token;

			final StringBuffer r = new StringBuffer();
			try {
				final URL u = new URL(url);
				final URLConnection uc = u.openConnection();
				final int end = 4000;
				InputStreamReader isr = null;
				BufferedReader br = null;
				try {
					isr = new InputStreamReader(uc.getInputStream());
					br = new BufferedReader(isr);
					final int chk = 0;
					while ((url = br.readLine()) != null) {
						if ((chk >= 0) && ((chk < end))) {
							r.append(url).append('\n');
						}
					}
				} catch (final java.net.ConnectException cex) {
					r.append(cex.getMessage());
				} catch (final Exception ex) {
				} finally {
					try {
						br.close();
					} catch (final Exception ex) {
						return null;
					}
				}
			} catch (final Exception e) {
			}

			final LoginInfo loginInfo = new LoginInfo();
			final UserInfo userInfo = new UserInfo();
			try {
				final JsonFactory f = new JsonFactory();
				JsonParser jp;
				jp = f.createJsonParser(r.toString());
				jp.nextToken();
				while (jp.nextToken() != JsonToken.END_OBJECT) {
					final String fieldname = jp.getCurrentName();
					jp.nextToken();
					switch (fieldname) {
					case "given_name":
						userInfo.setName(jp.getText());
						loginInfo.setName(jp.getText());
						break;
					case "family_name":
						userInfo.setLastName(jp.getText());
						break;
					case "email":
						userInfo.setEmail(jp.getText());
						loginInfo.setEmailAddress(jp.getText());
						break;
					case "picture":
						userInfo.setPictureUrl(jp.getText());
						loginInfo.setPictureUrl(jp.getText());
						break;
					default:
						break;
					}
					
				}
			} catch (final JsonParseException e) {
			} catch (final IOException e) {
			}
			return userInfo;
		}

		// TODO #11:> end
	
}
