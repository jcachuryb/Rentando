package co.edu.unal.rentando.shared.many2many;

public interface IUser {
	void setId(String id);

	String getId();
	
	void setDocument(String document);
	
	String getDocument();

	void setRole(UserRole role);

	UserRole getRole();

	void setName(String name);

	String getName();

	void setLastName(String lastName);

	String getLastName();

	void setPhoneNumber(String phone);

	String getPhoneNumber();

	void setAddress(String address);

	String getAddress();

	public static enum UserRole {
		normal_user, admin_user, outside_user;
	}
}
