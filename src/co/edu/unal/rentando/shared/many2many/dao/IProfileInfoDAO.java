package co.edu.unal.rentando.shared.many2many.dao;

import java.util.List;

import co.edu.unal.rentando.shared.many2many.IProfileInfo;

public interface IProfileInfoDAO {
	IProfileInfo loadUser(String id);

	void saveUser(IProfileInfo user);
	
	List<IProfileInfo> fetchAllUsers();
	
}
