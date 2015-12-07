package co.edu.unal.rentando.shared.many2many;

import co.edu.unal.rentando.shared.many2many.ofy.ExtraInfo;

public interface INormalUser {

	void setTotalPayed(String value);

	String getTotalPayed();

	void setExtraInfo(ExtraInfo xInfo);

	ExtraInfo getExtraInfo();

	void setCurentRent(IRent rent);

	IRent getCurentRent();

	String getId();

	void setId(String id);

}
