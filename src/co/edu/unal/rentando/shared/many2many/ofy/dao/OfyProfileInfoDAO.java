package co.edu.unal.rentando.shared.many2many.ofy.dao;

import static co.edu.unal.rentando.service.ofy.OfyService.ofy;

import java.util.LinkedList;
import java.util.List;

import co.edu.unal.rentando.shared.many2many.IProfileInfo;
import co.edu.unal.rentando.shared.many2many.dao.IProfileInfoDAO;
import co.edu.unal.rentando.shared.many2many.ofy.OfyProfileInfo;

import com.google.inject.Singleton;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

@Singleton
public class OfyProfileInfoDAO implements IProfileInfoDAO {

	@Override
	public IProfileInfo loadUser(String id) {
		Key<OfyProfileInfo> key = Key.create(OfyProfileInfo.class, id);
		return ofy().load().key(key).now();
	}

	@Override
	public void saveUser(IProfileInfo user) {
		ofy().save().entity(user).now();
	}

	@Override
	public List<IProfileInfo> fetchAllUsers() {
		Query<OfyProfileInfo> q = ofy().load().type(OfyProfileInfo.class);

		return new LinkedList<IProfileInfo>(q.list());
	}

}
