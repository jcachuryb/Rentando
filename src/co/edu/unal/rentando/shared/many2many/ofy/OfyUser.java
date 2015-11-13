package co.edu.unal.rentando.shared.many2many.ofy;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import co.edu.unal.rentando.shared.many2many.IUser;

@Entity
public class OfyUser implements IUser {

	@Id
	private String id;
	private String doc;
	private UserRole role;
	private String name;
	private String lastName;
	private String phone;
	private String address;


	@Override
	public String getId() {
		return id;
	}

	@Override
	public UserRole getRole() {
		// TODO Auto-generated method stub
		return role;
	}

	@Override
	public String getDocument() {
		// TODO Auto-generated method stub
		return doc;
	}

	@Override
	public String getPhoneNumber() {
		// TODO Auto-generated method stub
		return phone;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String s) {
		this.name = s;
	}

	@Override
	public void setId(String s) {
		this.id = s;
	}

	@Override
	public void setRole(UserRole s) {
		this.role = s;
	}

	@Override
	public void setDocument(String s) {
		this.doc = s;
	}

	@Override
	public void setPhoneNumber(String s) {
		this.phone = s;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String s) {
		this.lastName = s;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String s) {
		this.address = s;
	}

	@Override
	public String toString() {
		return "Profile Info [id=" + id + ", doc=" + doc + ", role=" + role
				+ ", name=" + name + ", lastName=" + lastName + ", phone="
				+ phone + ", address=" + address + "]";
	}

	
	
}
