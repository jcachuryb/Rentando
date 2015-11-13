package co.edu.unal.rentando.shared.many2many.ofy;

import co.edu.unal.rentando.shared.ExtraInfo;
import co.edu.unal.rentando.shared.many2many.INormalUser;
import co.edu.unal.rentando.shared.many2many.IRent;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Subclass;

@Subclass(index = true)
public class OfyNormalUser extends OfyUser implements INormalUser {

	private String total;
	private ExtraInfo extraInfo;
	private Ref<IRent> rent;

	@Override
	public void setTotalPayed(String value) {
		// TODO Auto-generated method stub
		this.total = value;
	}

	@Override
	public String getTotalPayed() {
		// TODO Auto-generated method stub
		return total;
	}

	@Override
	public void setExtraInfo(ExtraInfo xInfo) {
		// TODO Auto-generated method stub
		this.extraInfo = xInfo;
	}

	@Override
	public ExtraInfo getExtraInfo() {
		// TODO Auto-generated method stub
		return extraInfo;
	}

	@Override
	public void setCurentRent(IRent rent) {
		this.rent = Ref.create(rent);
	}

	@Override
	public IRent getCurentRent() {
		// TODO Auto-generated method stub
		return rent.get();
	}

	@Override
	public String toString() {
		return super.toString() + "\n"
				+ "Normal User Info [total=" + total + ", extraInfo=" + extraInfo
				+ ", rent=" + rent + "]";
	}

	
	
}
