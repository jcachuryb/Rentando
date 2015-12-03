package co.edu.unal.rentando.shared.many2many.ofy.dao;

import static co.edu.unal.rentando.service.ofy.OfyService.ofy;
import co.edu.unal.rentando.shared.many2many.IRent;
import co.edu.unal.rentando.shared.many2many.dao.IRentDAO;

import com.google.inject.Singleton;
import com.googlecode.objectify.Key;

@Singleton
public class OfyRentDAO implements IRentDAO{


	@Override
	public void saveRent(IRent rent) {
		ofy().save().entity(rent).now();
	}

	@Override
	public IRent loadRent(String id) {
		Key<IRent> key = Key.create(IRent.class, id);
		return ofy().load().key(key).now();
	}

}
