package co.edu.unal.rentando.shared.many2many.dao;

import java.util.ArrayList;

import co.edu.unal.rentando.shared.many2many.INormalUser;

public interface INormalUserDAO {

	INormalUser loadUser(String email);
	
	void saveUser(INormalUser user);
	
	ArrayList<INormalUser> fetchUsers();
	
}
