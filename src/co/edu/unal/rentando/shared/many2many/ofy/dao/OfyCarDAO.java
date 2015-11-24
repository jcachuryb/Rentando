package co.edu.unal.rentando.shared.many2many.ofy.dao;

import static co.edu.unal.rentando.service.ofy.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.shared.many2many.ICar;
import co.edu.unal.rentando.shared.many2many.dao.ICarDAO;
import co.edu.unal.rentando.shared.many2many.ofy.OfyCar;

import com.google.inject.Singleton;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

@Singleton
public class OfyCarDAO implements ICarDAO {

	@Override
	public void saveCar(ICar car) {
		ofy().save().entity(car).now();
	}

	@Override
	public ICar loadCar(String id) {
		Key<OfyCar> key = Key.create(OfyCar.class, id);
		return ofy().load().key(key).now();
	}

	@Override
	public List<ICar> fetchAllCars() {
		Query<OfyCar> q = ofy().load().type(OfyCar.class);

		return new ArrayList<ICar>(q.list());
	}

	@Override
	public void removeCar(String id) {
		Key<OfyCar> key = Key.create(OfyCar.class, id);
		ofy().delete().key(key).now();

	}

	@Override
	public Boolean carExists(String id) {
		Key<OfyCar> key = Key.create(OfyCar.class, id);
		return ofy().load().key(key).now() != null;
	}

}
