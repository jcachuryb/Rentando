package co.edu.unal.rentando.shared.many2many;

import java.util.List;

import co.edu.unal.rentando.shared.RentInfo;
import co.edu.unal.rentando.shared.RentalDate;

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

	boolean isAvailable(RentalDate rent);
	
	void  doRent(IRent rent);

	List<IRent> getRentals();
	
	void setRentals(List<IRent> list);

	void removeRental(RentInfo value);

	void clearRentals();
}
