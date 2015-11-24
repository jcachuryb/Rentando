package co.edu.unal.rentando.service.ofy;

import static com.googlecode.objectify.ObjectifyService.factory;
import co.edu.unal.rentando.shared.many2many.ofy.OfyAdminUser;
import co.edu.unal.rentando.shared.many2many.ofy.OfyCar;
import co.edu.unal.rentando.shared.many2many.ofy.OfyNormalUser;
import co.edu.unal.rentando.shared.many2many.ofy.OfyRental;
import co.edu.unal.rentando.shared.many2many.ofy.OfyProfileInfo;
import co.edu.unal.rentando.shared.many2many.ofy.OfyUsrLogin;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {

	static {

		factory().register(OfyAdminUser.class);
		factory().register(OfyNormalUser.class);
		factory().register(OfyCar.class);
		factory().register(OfyRental.class);
		factory().register(OfyProfileInfo.class);
		factory().register(OfyUsrLogin.class);
	}

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}
}
