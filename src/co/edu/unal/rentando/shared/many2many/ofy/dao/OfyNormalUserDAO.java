package co.edu.unal.rentando.shared.many2many.ofy.dao;

import static co.edu.unal.rentando.service.ofy.OfyService.ofy;

import java.util.ArrayList;

import co.edu.unal.rentando.shared.many2many.INormalUser;
import co.edu.unal.rentando.shared.many2many.dao.INormalUserDAO;
import co.edu.unal.rentando.shared.many2many.ofy.OfyNormalUser;

import com.google.inject.Singleton;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

@Singleton
public class OfyNormalUserDAO implements INormalUserDAO {

	@Override
	public INormalUser loadUser(String email) {
		Key<OfyNormalUser> key = Key.create(OfyNormalUser.class, email);
		return ofy().load().key(key).now();
	}

	@Override
	public void saveUser(INormalUser user) {
		ofy().save().entity(user).now();
	}

	@Override
	public ArrayList<INormalUser> fetchUsers() {
		Query<OfyNormalUser> q = ofy().load().type(OfyNormalUser.class);
		return new ArrayList<INormalUser>(q.list());
	}
}
