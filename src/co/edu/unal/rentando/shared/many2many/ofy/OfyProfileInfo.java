package co.edu.unal.rentando.shared.many2many.ofy;

import co.edu.unal.rentando.shared.many2many.IProfileInfo;
import co.edu.unal.rentando.shared.many2many.IUsrLogin.UserRole;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class OfyProfileInfo implements IProfileInfo {

	@Id
	private String id;
	private String doc;
	private String name;
	private String lastName;
	private String phone;
	private String address;

	@Override
	public String getId() {
		return id;
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

	// @Override
	// public void setRole(UserRole s) {
	// this.role = s;
	// }

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
		return "OfyProfileInfo [id=" + id + ", doc=" + doc + ", name=" + name
				+ ", lastName=" + lastName + ", phone=" + phone + ", address="
				+ address + "]";
	}

	public IProfileInfo getProfileInfo() {
		return this;
	}

}
