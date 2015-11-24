package co.edu.unal.rentando.client;

import java.util.List;

import co.edu.unal.rentando.shared.CarInfo;
import co.edu.unal.rentando.shared.ExtraInfo;
import co.edu.unal.rentando.shared.LoginInfo;
import co.edu.unal.rentando.shared.NormalUserInfo;
import co.edu.unal.rentando.shared.UserInfo;
import co.edu.unal.rentando.shared.UsrLoginInfo;

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

	UsrLoginInfo loginDetails(String token);

	// TODO #09:> end

	String saveCar(CarInfo id);

	CarInfo getCar(String id);

	String deleteCar(String id);

	List<CarInfo> fetchCars();

	// Profile Server functions

	UserInfo getUserInfo(String id);

	String saveUserInfo(UserInfo info);

	// UsrLoginInfo Server functions

	UsrLoginInfo getLoginInfo(String id);

	String saveLoginInfo(UsrLoginInfo info);

	List<UsrLoginInfo> fetchAllLogins();
	
	String saveLoginInfoBatch(List<UsrLoginInfo> changes);

	// String saveSeveralLoginInfo(List<usrLoginInfo> infoList);

	// NormalUser Server functions

	NormalUserInfo getNormalUser(String id);

	String saveNormalUser(NormalUserInfo info);

	List<NormalUserInfo> fetchAllNormalUsers();

	ExtraInfo getExtraInfo(String id);
	
	UsrLoginInfo createUser(UsrLoginInfo info);

}
