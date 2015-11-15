package co.edu.unal.rentando.shared.many2many.dao;

import java.util.List;

import co.edu.unal.rentando.shared.many2many.IUser;

public interface IUserDAO {
	IUser loadUser(String id);

	void saveUser(IUser user);
	
	List<IUser> fetchAllUsers();
	
}
