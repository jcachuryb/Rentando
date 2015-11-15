package co.edu.unal.rentando.client.presenter;

import co.edu.unal.rentando.client.RentandoServiceAsync;
import co.edu.unal.rentando.client.presenter.MainBarPresenter.MenuItemLists;
import co.edu.unal.rentando.client.view.MainView;
import co.edu.unal.rentando.shared.UserInfo;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class LoginPresenter extends Presenter implements IPresenter {

	MainBarPresenter mainBarPresenter;
	
	public interface Display {
		
	}

	public final Display display;
	public UserInfo userInfo;

	public LoginPresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		super(rpcService, eventBus);
		this.display = view;
		this.userInfo = new UserInfo(); 
		mainBarPresenter = new MainBarPresenter(rpcService, eventBus, new MainView(MenuItemLists.unknown));
		// TODO Auto-generated constructor stub
	}

	private void bind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		this.mainBarPresenter.go(container);
		bind();
		
	}

}
