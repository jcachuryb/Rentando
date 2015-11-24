package co.edu.unal.rentando.shared.many2many.ofy;

import co.edu.unal.rentando.shared.ExtraInfo;
import co.edu.unal.rentando.shared.many2many.INormalUser;
import co.edu.unal.rentando.shared.many2many.IProfileInfo;
import co.edu.unal.rentando.shared.many2many.IRent;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Subclass;

@Entity
@Subclass
public class OfyNormalUser extends OfyProfileInfo implements INormalUser {

	private String total;
	private ExtraInfo extraInfo;
	private Ref<IRent> rent;
	@Ignore
	private IProfileInfo profile;

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
		if (rent == null) {
			this.rent = null;
		} else {
			this.rent = Ref.create(rent);
		}
	}

	@Override
	public IRent getCurentRent() {
		if (rent == null) {
			return null;
		}
		return rent.get();
	}

	@Override
	public String toString() {
		return super.toString() + "\n" + "Normal User Info [total=" + total
				+ ", extraInfo=" + extraInfo + ", rent=" + rent + "]";
	}


}
