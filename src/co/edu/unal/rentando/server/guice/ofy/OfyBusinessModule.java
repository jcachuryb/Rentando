package co.edu.unal.rentando.server.guice.ofy;

import co.edu.unal.rentando.server.util.ICarProvider;
import co.edu.unal.rentando.server.util.INormalUserProvider;
import co.edu.unal.rentando.server.util.IProfileInfoProvider;
import co.edu.unal.rentando.server.util.IUsrLoginProvider;
import co.edu.unal.rentando.server.util.impl.ICarProviderImpl;
import co.edu.unal.rentando.server.util.impl.INormalUserProviderImpl;
import co.edu.unal.rentando.server.util.impl.IProfileInfoProviderImpl;
import co.edu.unal.rentando.server.util.impl.IUsrLoginProviderImpl;
import co.edu.unal.rentando.shared.many2many.IAdminUser;
import co.edu.unal.rentando.shared.many2many.ICar;
import co.edu.unal.rentando.shared.many2many.INormalUser;
import co.edu.unal.rentando.shared.many2many.IRent;
import co.edu.unal.rentando.shared.many2many.IProfileInfo;
import co.edu.unal.rentando.shared.many2many.IUsrLogin;
import co.edu.unal.rentando.shared.many2many.dao.ICarDAO;
import co.edu.unal.rentando.shared.many2many.dao.INormalUserDAO;
import co.edu.unal.rentando.shared.many2many.dao.IProfileInfoDAO;
import co.edu.unal.rentando.shared.many2many.dao.IUsrLoginDAO;
import co.edu.unal.rentando.shared.many2many.ofy.OfyAdminUser;
import co.edu.unal.rentando.shared.many2many.ofy.OfyCar;
import co.edu.unal.rentando.shared.many2many.ofy.OfyNormalUser;
import co.edu.unal.rentando.shared.many2many.ofy.OfyRental;
import co.edu.unal.rentando.shared.many2many.ofy.OfyProfileInfo;
import co.edu.unal.rentando.shared.many2many.ofy.OfyUsrLogin;
import co.edu.unal.rentando.shared.many2many.ofy.dao.OfyCarDAO;
import co.edu.unal.rentando.shared.many2many.ofy.dao.OfyNormalUserDAO;
import co.edu.unal.rentando.shared.many2many.ofy.dao.OfyProfileInfoDAO;
import co.edu.unal.rentando.shared.many2many.ofy.dao.OfyUsrLoginDAO;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.googlecode.objectify.ObjectifyFilter;

public class OfyBusinessModule extends AbstractModule {
	private static boolean isActive = false;

	@Override
	protected void configure() {
		isActive = true;
		System.out.println("*** OfyBusinessModule::configure(): "
				+ "binding interfaces to OFY classes");
		bind(ObjectifyFilter.class).in(Singleton.class);
		bind(IProfileInfo.class).to(OfyProfileInfo.class);
		bind(INormalUser.class).to(OfyNormalUser.class);
		bind(IAdminUser.class).to(OfyAdminUser.class);
		bind(ICar.class).to(OfyCar.class);
		bind(IRent.class).to(OfyRental.class);
		bind(IUsrLogin.class).to(OfyUsrLogin.class);

		bind(IProfileInfoDAO.class).to(OfyProfileInfoDAO.class);
		bind(ICarDAO.class).to(OfyCarDAO.class);
		bind(IUsrLoginDAO.class).to(OfyUsrLoginDAO.class);
		bind(INormalUserDAO.class).to(OfyNormalUserDAO.class);

		bind(ICarProvider.class).to(ICarProviderImpl.class);
		bind(IUsrLoginProvider.class).to(IUsrLoginProviderImpl.class);
		bind(IProfileInfoProvider.class).to(IProfileInfoProviderImpl.class);
		bind(INormalUserProvider.class).to(INormalUserProviderImpl.class);
	}

	public static boolean isActive() {
		return isActive;
	}
}
