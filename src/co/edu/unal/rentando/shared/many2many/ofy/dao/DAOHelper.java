package co.edu.unal.rentando.shared.many2many.ofy.dao;

import com.google.inject.Injector;

import co.edu.unal.rentando.shared.many2many.dao.ICarDAO;
import co.edu.unal.rentando.shared.many2many.dao.INormalUserDAO;
import co.edu.unal.rentando.shared.many2many.dao.IProfileInfoDAO;
import co.edu.unal.rentando.shared.many2many.dao.IRentDAO;
import co.edu.unal.rentando.shared.many2many.dao.IUsrLoginDAO;

public class DAOHelper {

	private IUsrLoginDAO loginDAO;
	private ICarDAO carDao;
	private IProfileInfoDAO profileDao;
	private INormalUserDAO normalUserDao;
	private IRentDAO rentDao;
	private static DAOHelper instance;

	public DAOHelper(Injector injector) {
		loginDAO = injector.getInstance(IUsrLoginDAO.class);
		carDao = injector.getInstance(ICarDAO.class);
		profileDao = injector.getInstance(IProfileInfoDAO.class);
		normalUserDao = injector.getInstance(INormalUserDAO.class);
		rentDao = injector.getInstance(IRentDAO.class);
	}

	public static void initialize(Injector injector) {
		if (instance == null) {
			instance = new DAOHelper(injector);
		}
	}

	public static DAOHelper getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

	public IUsrLoginDAO getLoginDAO() {
		return loginDAO;
	}

	public ICarDAO getCarDao() {
		return carDao;
	}

	public IProfileInfoDAO getProfileDao() {
		return profileDao;
	}

	public INormalUserDAO getNormalUserDao() {
		return normalUserDao;
	}
	
	public IRentDAO getRentDao(){
		return rentDao;
	}

	
	
}
