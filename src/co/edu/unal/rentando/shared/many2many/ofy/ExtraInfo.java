package co.edu.unal.rentando.shared.many2many.ofy;

import java.io.Serializable;

public class ExtraInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String creditCard;
	private String brand;
	private String license;

	public void setCreditCardNumber(String number) {
		this.creditCard = number;
	}

	public String getCreditCardNumber() {
		// TODO Auto-generated method stub
		return creditCard;
	}

	public void setCreditCardBrand(String brand) {
		this.brand = brand;
	}

	public String getCreditCardBrand() {
		// TODO Auto-generated method stub
		return brand;
	}

	public void setDriverLicense(String license) {
		this.license = license;
	}

	public String getDriverLicense() {
		// TODO Auto-generated method stub
		return license;
	}
}
