package co.edu.unal.rentando.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import co.edu.unal.rentando.client.RentandoService;
import co.edu.unal.rentando.client.presenter.AdminCarPresenter;
import co.edu.unal.rentando.server.guice.ofy.OfyBusinessModule;
import co.edu.unal.rentando.server.guice.sql.SqlBusinessModule;
import co.edu.unal.rentando.server.util.impl.ProviderHelper;
import co.edu.unal.rentando.shared.CarInfo;
import co.edu.unal.rentando.shared.LoginInfo;
import co.edu.unal.rentando.shared.NormalUserInfo;
import co.edu.unal.rentando.shared.RentInfo;
import co.edu.unal.rentando.shared.UserInfo;
import co.edu.unal.rentando.shared.UsrLoginInfo;
import co.edu.unal.rentando.shared.many2many.ICar;
import co.edu.unal.rentando.shared.many2many.INormalUser;
import co.edu.unal.rentando.shared.many2many.IProfileInfo;
import co.edu.unal.rentando.shared.many2many.IRent;
import co.edu.unal.rentando.shared.many2many.IUsrLogin;
import co.edu.unal.rentando.shared.many2many.IUsrLogin.UserRole;
import co.edu.unal.rentando.shared.many2many.ofy.ExtraInfo;
import co.edu.unal.rentando.shared.many2many.ofy.dao.DAOHelper;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RentandoServiceImpl extends RemoteServiceServlet implements
		RentandoService {
	Injector injector;

	ProviderHelper providers;
	DAOHelper daoHelper;

	public RentandoServiceImpl() {
		if (OfyBusinessModule.isActive()) {
			injector = Guice.createInjector(new OfyBusinessModule());
		} else {
			injector = Guice.createInjector(new SqlBusinessModule());
		}

		ProviderHelper.initialize(injector);
		providers = ProviderHelper.getInstance();
		DAOHelper.initialize(injector);
		daoHelper = DAOHelper.getInstance();

		checkForSuperAdmins();
	}

	private void checkForSuperAdmins() {
		try {
			ArrayList<String> sudos = new ArrayList<String>();
			sudos.add("truancamilo@gmail.com");
			sudos.add("jcachuryb@unal.edu.co");
			ArrayList<UserRole> roles = new ArrayList<IUsrLogin.UserRole>();
			for (String string : sudos) {
				IUsrLogin login = daoHelper.getLoginDAO().load(string);
				if (login == null) {
					login = providers.getLoginProvider().getNewLogin();
					roles.clear();
					roles.add(UserRole.super_admin);
					login.setId(string);
					login.setUserRoles(roles);
					login.setUserExists(true);
					
				} else {
					if (!login.getUserRoles().contains(UserRole.super_admin)) {
						login.getUserRoles().add(UserRole.super_admin);
					}
				}
				daoHelper.getLoginDAO().save(login);
				createUser(convertToUsrLoginInfo(login));
			}
		} catch (Exception e) {
			System.out.println("Error saving sudos: \n" + e.toString());
		}
	}

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
	public UsrLoginInfo loginDetails(final String token) {
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
				// case "picture":
				// userInfo.setPictureUrl(jp.getText());
				// loginInfo.setPictureUrl(jp.getText());
				// break;
				default:
					break;
				}

			}
		} catch (final JsonParseException e) {
		} catch (final IOException e) {
		}

		// Check if User exists
		String userEmail = userInfo.getEmail();
		IUsrLogin usrLogin = this.daoHelper.getLoginDAO().load(userEmail);
		if (usrLogin == null && userEmail != null) {
			usrLogin = providers.getLoginProvider().getNewLogin();
			usrLogin.setId(userEmail);
			ArrayList<UserRole> roles = new ArrayList<IUsrLogin.UserRole>();
			roles.add(UserRole.normal_user);
			usrLogin.setUserRoles(roles);
			usrLogin.setUserExists(true);
			daoHelper.getLoginDAO().save(usrLogin);
			createUser(convertToUsrLoginInfo(usrLogin));
		}
		return convertToUsrLoginInfo(usrLogin);
	}

	@Override
	public UsrLoginInfo createUser(UsrLoginInfo info) {
		String email = info.getId();
		IProfileInfo profile = providers.getProfileProvider()
				.getNewProfileInfo();

		profile.setId(email);
		profile.setName(email.substring(0, info.getId().indexOf("@")));

		daoHelper.getProfileDao().saveUser(profile);

		if (info.getRoles().contains(UserRole.normal_user)) {
			try {
				INormalUser normalUser = providers.getNormalUserProvider()
						.getuser();
				normalUser.setId(email);
				normalUser.setTotalPayed("0");
				normalUser.setExtraInfo(new ExtraInfo());
				daoHelper.getNormalUserDao().saveUser(normalUser);
			} catch (Exception e) {
				System.out.println("Error saving Normal User: " + e);
			}
		}

		info.setUserExistance(true);
		daoHelper.getLoginDAO().save(convertToIUsrLogin(info));
		return info;
	}

	@Override
	public String saveCar(CarInfo carInfo) {
		if (this.daoHelper.getCarDao().carExists(carInfo.getId())
				&& carInfo.isNew()) {
			return AdminCarPresenter.UPD_REPEATED;
		}
		ICar car = convertToICar(carInfo);
		try {
			this.daoHelper.getCarDao().saveCar(car);
			return AdminCarPresenter.UPD_OK;
		} catch (Exception e) {
			System.err.println("*********** We're fucked up!! \n" + e);
			return AdminCarPresenter.UPD_ERROR;
		}
	}

	@Override
	public CarInfo getCar(String id) {
		// TODO Auto-generated method stub
		CarInfo car = new CarInfo();
		ICar bdCar = this.daoHelper.getCarDao().loadCar(id);
		if (bdCar != null) {
			car.fillOutCar(bdCar);
		} else {
			car.setIsNew(true);
		}
		return car;
	}

	@Override
	public synchronized String deleteCar(String id) {
		if (this.daoHelper.getCarDao().carExists(id)) {
			this.daoHelper.getCarDao().removeCar(id);
			return AdminCarPresenter.UPD_OK;
		}
		return AdminCarPresenter.UPD_ERROR;
	}

	@Override
	public List<CarInfo> fetchCars() {
		List<ICar> iCarList = this.daoHelper.getCarDao().fetchAllCars();
		if (iCarList == null) {
			return new ArrayList<CarInfo>();
		}
		ArrayList<CarInfo> carInfoList = new ArrayList<>();
		for (ICar item : iCarList) {
			CarInfo newCar = new CarInfo();
			newCar.fillOutCar(item);
			carInfoList.add(newCar);
		}
		return carInfoList;
	}

	// TODO #11:> end

	private List<IRent> getIRentList(List<RentInfo> list) {
		List<IRent> newList = new ArrayList<IRent>();
		for (RentInfo info : list) {
			newList.add(convertToIRent(info));
		}

		return newList;
	}

	public List<RentInfo> getRentInfoList(List<IRent> list) {
		List<RentInfo> newList = new ArrayList<RentInfo>();
		for (IRent info : list) {
			newList.add(convertToRentInfo(info));
		}

		return newList;
	}

	private UserInfo converTotUserInfo(IProfileInfo info) {
		UserInfo userInfo = new UserInfo();
		userInfo.setEmail(info.getId());
		userInfo.setName(info.getName());
		userInfo.setLastName(info.getLastName());
		userInfo.setDocument(info.getDocument());
		userInfo.setAddress(info.getAddress());
		userInfo.setPhoneNumber(info.getPhoneNumber());

		return userInfo;
	}

	private IProfileInfo converToProfileInfo(UserInfo info) {
		IProfileInfo userInfo = providers.getProfileProvider()
				.getNewProfileInfo();
		userInfo.setId(info.getEmail());
		userInfo.setName(info.getName());
		userInfo.setLastName(info.getLastName());
		userInfo.setDocument(info.getDocument());
		userInfo.setAddress(info.getAddress());
		userInfo.setPhoneNumber(info.getPhoneNumber());

		return userInfo;
	}

	private UsrLoginInfo convertToUsrLoginInfo(IUsrLogin info) {
		UsrLoginInfo loginInfo = new UsrLoginInfo();
		loginInfo.setId(info.getId());
		loginInfo.setRoles(info.getUserRoles());
		loginInfo.setUserExistance(info.userExists());
		return loginInfo;
	}

	private IUsrLogin convertToIUsrLogin(UsrLoginInfo info) {
		IUsrLogin loginInfo = providers.getLoginProvider().getNewLogin();
		loginInfo.setId(info.getId());
		loginInfo.setUserRoles(info.getRoles());
		loginInfo.setUserExists(info.userExists());
		return loginInfo;
	}

	public RentInfo convertToRentInfo(IRent rent) {
		RentInfo rentInfo = new RentInfo();
		rentInfo.setId(rent.getId());
		rentInfo.setInitDate(rent.getInitDate());
		rentInfo.setDueDate(rent.getDueDate());
		rentInfo.setCar(convertToCarInfo(rent.getCar()));

		return rentInfo;
	}

	private IRent convertToIRent(RentInfo rent) {
		IRent rentInfo = providers.getRentProvider().getNewRent();
		rentInfo.setId(rent.getId());
		rentInfo.setInitialDate(rent.getInitDate());
		rentInfo.setDueDate(rent.getDueDate());
		rentInfo.setCar(convertToICar(rent.getCar()));

		return rentInfo;
	}

	public CarInfo convertToCarInfo(ICar car) {
		CarInfo info = new CarInfo();
		info.setId(car.getId());
		info.setBrand(car.getBrand());
		info.setPictURL(car.getPicture());
		info.setDescription(car.getDescription());
		info.setReference(car.getReference());
		info.setPrice(car.getRentalPrice());

		return info;
	}

	private ICar convertToICar(CarInfo car) {
		ICar info = providers.getCarProvider().getNewCar();
		info.setId(car.getId());
		info.setBrand(car.getBrand());
		info.setPicture(car.getPictURL());
		info.setDescription(car.getDescription());
		info.setReference(car.getReference());
		info.setRentalPrice(car.getPrice());
		info.setRentals(getIRentList(car.getRentals()));
		return info;
	}

	@Override
	public UserInfo getUserInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveUserInfo(UserInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsrLoginInfo getLoginInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveLoginInfo(UsrLoginInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveLoginInfoBatch(List<UsrLoginInfo> changes) {
		for (UsrLoginInfo usrLoginInfo : changes) {
			daoHelper.getLoginDAO().save(convertToIUsrLogin(usrLoginInfo));
		}
		return "Listo";
	}

	@Override
	public List<UsrLoginInfo> fetchAllLogins() {
		ArrayList<UsrLoginInfo> usrList = new ArrayList<UsrLoginInfo>();
		List<IUsrLogin> fetched = daoHelper.getLoginDAO().fetchLogin();
		for (IUsrLogin iUsrLogin : fetched) {
			usrList.add(convertToUsrLoginInfo(iUsrLogin));
		}

		return usrList;
	}

	@Override
	public NormalUserInfo getNormalUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveNormalUser(NormalUserInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NormalUserInfo> fetchAllNormalUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExtraInfo getExtraInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized String saveRent(RentInfo info) {
		// TODO
		IRent cc = daoHelper.getRentDao().loadRent(info.getId());
		if (cc != null) {
			return "El usuario tiene un alquiler vigente.";
		}

		CarInfo car = info.getCar();
		ICar carInfo = daoHelper.getCarDao().loadCar(car.getId());
		List<IRent> rentals = carInfo.getRentals();
		rentals = new ArrayList<IRent>();
		boolean validDates = true;
		for (IRent rent : rentals) {
			if (!info.getInitDate().after(rent.getDueDate())) {
				if (!(info.getInitDate().before(rent.getInitDate()) && info
						.getDueDate().before(rent.getInitDate()))) {
					validDates = false;
					break;
				}
			} else {
				if (info.getInitDate().equals(rent.getDueDate())) {
					validDates = false;
					break;
				}
			}
		}
		if (validDates) {
			try {
				IRent nRent = convertToIRent(info);
				daoHelper.getRentDao().saveRent(nRent);
				carInfo.doRent(nRent);
				daoHelper.getCarDao().saveCar(carInfo);

				return "Success";
			} catch (Exception e) {
				return "Error: " + e.toString();
			}
		}
		// TODO Auto-generated method stub
		return "Invalid";
	}

	@Override
	public RentInfo loadRent(String id) {
		IRent rent = daoHelper.getRentDao().loadRent(id);
		RentInfo info;
		if (rent != null) {
			info = convertToRentInfo(rent);
		} else {
			info = new RentInfo();
			info.setId("");
		}
		return info;
	}

	@Override
	public List<RentInfo> fetchRentals() {
		List<IRent> list = daoHelper.getRentDao().fetchRentals();
		List<RentInfo> info = new ArrayList<RentInfo>();
		if (list != null) {
			for (IRent rent : list) {
				info.add(convertToRentInfo(rent));
			}
		}
		return info;
	}

	@Override
	public List<RentInfo> fetchCarAssocRents(String id) {
		ICar car = daoHelper.getCarDao().loadCar(id);
		List<IRent> rentals = car.getRentals();

		// TODO Auto-generated method stub
		return getRentInfoList(rentals);
	}

	@Override
	public String registerReturnedCar(RentInfo info) {
		ICar car = daoHelper.getCarDao().loadCar(info.getCar().getId());
		for (IRent r : car.getRentals()) {
			if (r.getId().equals(info.getId())) {
				car.removeRental(convertToRentInfo(r));
				break;
			}
		}
		daoHelper.getCarDao().saveCar(car);
		daoHelper.getRentDao().remove(info.getId());
		return "Listo";
	}

}
