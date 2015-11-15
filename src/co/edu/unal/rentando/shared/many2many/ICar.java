package co.edu.unal.rentando.shared.many2many;

import java.util.List;

public interface ICar {
	void setId(Integer id);

	Integer getId();

	void setPicture(String pictURL);

	String getPicture();

	void setBrand(String brand);

	String getBrand();

	void setReference(String ref);

	String getReference();

	void setDescription(String desc);

	String getDescription();

	void setRentalPrice(String price);

	String getRentalPrice();

	void  doRent(IRent rent);

	List<IRent> getRentals();

	void removeRental(IRent value);

	void clearRentals();
}
