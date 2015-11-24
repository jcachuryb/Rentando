package co.edu.unal.rentando.shared.many2many;

import java.util.List;

public interface IUsrLogin {

	void setId(String id);
	
	String getId();
	
	void setUserRoles(List<UserRole> role);
	
	void removeUserRole(UserRole role);
	
	List<UserRole> getUserRoles();
	
	void setUserExists(boolean b);
	
	boolean userExists();

	public static enum UserRole {
		normal_user, admin_user, outside_user, super_admin;
	}
}
