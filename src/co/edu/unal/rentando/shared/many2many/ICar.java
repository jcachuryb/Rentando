package co.edu.unal.rentando.shared.many2many;

import java.util.List;

import co.edu.unal.rentando.shared.RentInfo;

public interface ICar {
	void setId(String id);

	String getId();

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

	void  doRent(RentInfo rent);

	List<RentInfo> getRentals();
	
	void setRentals(List<RentInfo> list);

	void removeRental(RentInfo value);

	void clearRentals();
}
