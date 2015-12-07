package co.edu.unal.rentando.shared.many2many.ofy.dao;

import static co.edu.unal.rentando.service.ofy.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.shared.many2many.IRent;
import co.edu.unal.rentando.shared.many2many.dao.IRentDAO;
import co.edu.unal.rentando.shared.many2many.ofy.OfyRental;

import com.google.inject.Singleton;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

@Singleton
public class OfyRentDAO implements IRentDAO {

	@Override
	public void saveRent(IRent rent) {
		ofy().save().entity(rent).now();
	}

	@Override
	public IRent loadRent(String id) {
		Key<OfyRental> key = Key.create(OfyRental.class, id);
		return ofy().load().key(key).now();
	}

	@Override
	public List<IRent> fetchRentals() {
		Query<OfyRental> q = ofy().load().type(OfyRental.class);

		return new ArrayList<IRent>(q.list());
	}

	@Override
	public void remove(String id) {
		Key<OfyRental> key = Key.create(OfyRental.class, id);
		ofy().delete().key(key).now();
	}

}
