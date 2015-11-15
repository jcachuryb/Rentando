package co.edu.unal.rentando.shared.many2many.ofy.dao;

import static co.edu.unal.rentando.service.ofy.OfyService.ofy;

import java.util.LinkedList;
import java.util.List;

import co.edu.unal.rentando.shared.many2many.IUser;
import co.edu.unal.rentando.shared.many2many.dao.IUserDAO;
import co.edu.unal.rentando.shared.many2many.ofy.OfyUser;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

public class OfyUserDAO implements IUserDAO {

	@Override
	public IUser loadUser(String id) {
		Key<OfyUser> key = Key.create(OfyUser.class, id);
		return ofy().load().key(key).now();
	}

	@Override
	public void saveUser(IUser user) {
		ofy().save().entity(user).now();
	}

	@Override
	public List<IUser> fetchAllUsers() {
		Query<OfyUser> q = ofy().load().type(OfyUser.class);

		return new LinkedList<IUser>(q.list());
	}

}
