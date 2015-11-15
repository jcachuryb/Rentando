package co.edu.unal.rentando.shared;

import java.io.Serializable;
import java.util.List;

public class CarInfo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String pictURL;
	private String brand;
	private String reference;
	private String description;
	private String price;
	private List<RentInfo> rentals;
}
