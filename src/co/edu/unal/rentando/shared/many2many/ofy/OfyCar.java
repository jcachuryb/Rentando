package co.edu.unal.rentando.shared.many2many.ofy;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.shared.RentInfo;
import co.edu.unal.rentando.shared.RentalDate;
import co.edu.unal.rentando.shared.many2many.ICar;
import co.edu.unal.rentando.shared.many2many.IRent;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Load;

@Entity
public class OfyCar implements ICar {

	@Id
	private String id;
	private String pictURL;
	private String brand;
	private String reference;
	private String description;
	private String price;
	@Load
	private List<Ref<IRent>> refRentals;
	@Ignore
	private List<IRent> rentals;

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setPicture(String pictURL) {
		// TODO Auto-generated method stub
		this.pictURL = pictURL;
	}

	@Override
	public String getPicture() {
		// TODO Auto-generated method stub
		return this.pictURL;
	}

	@Override
	public void setBrand(String brand) {
		// TODO Auto-generated method stub
		this.brand = brand;
	}

	@Override
	public String getBrand() {
		// TODO Auto-generated method stub
		return this.brand;
	}

	@Override
	public void setReference(String model) {
		// TODO Auto-generated method stub
		this.reference = model;
	}

	@Override
	public String getReference() {
		// TODO Auto-generated method stub
		return this.reference;
	}

	@Override
	public void setDescription(String desc) {
		// TODO Auto-generated method stub
		this.description = desc;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.description;
	}

	@Override
	public void setRentalPrice(String price) {
		// TODO Auto-generated method stub
		this.price = price;
	}

	@Override
	public String getRentalPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}

	@Override
	public boolean isAvailable(RentalDate rent) {

		return false;
	}

	@Override
	public synchronized void doRent(IRent rent) {
		refRentals.add(Ref.create(rent));
		rentals.add(rent);
	}

	@Override
	public List<IRent> getRentals() {
		if (rentals != null && refRentals != null) {
			if (rentals.size() != refRentals.size()) {
				rentals.clear();
				for (Ref<IRent> ref : refRentals) {
					rentals.add(ref.get());
				}
			}
		}
		else{
			rentals = new ArrayList<IRent>();
			refRentals = new ArrayList<Ref<IRent>>();
		}
		return rentals;
	}

	@Override
	public void removeRental(RentInfo value) {
		// TODO Auto-generated method stub
		try {
			rentals.remove(value);
			System.out.println("Objeto removido");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void clearRentals() {
		rentals.clear();
	}

	@Override
	public void setRentals(List<IRent> list) {
		this.rentals = list;

	}

}
