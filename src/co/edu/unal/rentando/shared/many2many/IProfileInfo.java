package co.edu.unal.rentando.shared.many2many;


public interface IProfileInfo {
	void setId(String id);

	String getId();
	
	void setDocument(String document);
	
	String getDocument();

	void setName(String name);

	String getName();

	void setLastName(String lastName);

	String getLastName();

	void setPhoneNumber(String phone);

	String getPhoneNumber();

	void setAddress(String address);

	String getAddress();

}
