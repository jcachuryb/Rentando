package co.edu.unal.rentando.client.presenter;

import co.edu.unal.rentando.client.RentandoServiceAsync;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class IndexPresenter extends Presenter implements IPresenter{
	public interface Display{
		
	}
	
	public IndexPresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus) {
		super(rpcService, eventBus);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}

}
