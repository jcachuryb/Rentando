package co.edu.unal.rentando.client.behavior;

import java.util.List;

import co.edu.unal.rentando.shared.RentInfo;

public interface LoadCarRentals {

	List<RentInfo> loadRentals(String id);
	
	void loadUserRental();
	
}
