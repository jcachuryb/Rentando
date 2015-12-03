package co.edu.unal.rentando.client;

import java.util.List;

import co.edu.unal.rentando.shared.CarInfo;
import co.edu.unal.rentando.shared.ExtraInfo;
import co.edu.unal.rentando.shared.LoginInfo;
import co.edu.unal.rentando.shared.NormalUserInfo;
import co.edu.unal.rentando.shared.RentInfo;
import co.edu.unal.rentando.shared.UserInfo;
import co.edu.unal.rentando.shared.UsrLoginInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface RentandoServiceAsync {

	// TODO #10: create login helper methods in service asynchronous interface
	void getUserEmail(String token, AsyncCallback<String> callback);

	void login(String requestUri, AsyncCallback<LoginInfo> asyncCallback);

	void loginDetails(String token, AsyncCallback<UsrLoginInfo> asyncCallback);

	// TODO #10:> end

	// ***** CAR Server Actions

	void saveCar(CarInfo id, AsyncCallback<String> asyncCallback);

	void getCar(String id, AsyncCallback<CarInfo> asyncCallback);

	void deleteCar(String id, AsyncCallback<String> asyncCallback);

	void fetchCars(AsyncCallback<List<CarInfo>> asyncCallback);

	// ***** USER Server Actions
	// Profile Server functions

	void getUserInfo(String id, AsyncCallback<UserInfo> callback);

	void saveUserInfo(UserInfo info, AsyncCallback<String> callback);

	// UsrLoginInfo Server functions

	void getLoginInfo(String id, AsyncCallback<UsrLoginInfo> callback);

	void saveLoginInfo(UsrLoginInfo info, AsyncCallback<String> callback);

	void fetchAllLogins(AsyncCallback<List<UsrLoginInfo>> callback);

	void saveLoginInfoBatch(List<UsrLoginInfo> changes,
			AsyncCallback<String> callback);

	// String saveSeveralLoginInfo(List<usrLoginInfo> infoList);

	// NormalUser Server functions

	void getNormalUser(String id, AsyncCallback<NormalUserInfo> callback);

	void saveNormalUser(NormalUserInfo info, AsyncCallback<String> callback);

	void fetchAllNormalUsers(AsyncCallback<List<NormalUserInfo>> callback);

	void getExtraInfo(String id, AsyncCallback<ExtraInfo> callback);

	void createUser(UsrLoginInfo info, AsyncCallback<UsrLoginInfo> callback);

	// RentInfo Server functions *******

	void saveRent(RentInfo info, AsyncCallback<String> callback);

	void loadRent(String id, AsyncCallback<RentInfo> callback);

	void fetchCarAssocRents(CarInfo car, AsyncCallback<List<RentInfo>> callback);
}
