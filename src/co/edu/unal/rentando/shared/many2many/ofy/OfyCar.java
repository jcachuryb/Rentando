package co.edu.unal.rentando.shared.many2many.ofy;

import java.util.Date;
import java.util.List;

import co.edu.unal.rentando.shared.many2many.ICar;
import co.edu.unal.rentando.shared.many2many.IRent;

import com.google.appengine.api.search.DateUtil;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


@Entity
public class OfyCar implements ICar {

	@Id private Integer id;
	private String pictURL;
	private String brand;
	private String reference;
	private String description;
	private String price;
	private List<IRent> rentals;
	
	
	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public Integer getId() {
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

	
	// TODO Hacer que funcione la parte de añadir rentas a la lista.
	@Override
	public synchronized void doRent(IRent rent) {
		// TODO Auto-generated method stub
		for (IRent current : rentals) {
			if (!current.getInitialDate().equals(rent.getInitialDate())) {
				
			}else{
				System.out.println("Hay un solape de fechas.");
			}
			 
		}
		
		if (rentals.indexOf(rent) != -1) {
			rentals.add(rent);
			System.out.println("Objeto añadido");
		}
	}

	@Override
	public List<IRent> getRentals() {
		// TODO Auto-generated method stub
		return rentals;
	}

	@Override
	public void removeRental(IRent value) {
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

}
