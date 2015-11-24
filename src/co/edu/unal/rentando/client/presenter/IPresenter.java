package co.edu.unal.rentando.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public interface IPresenter {
	public abstract void go(final HasWidgets container);
	public abstract void bind();
	public abstract Widget getMainWidget();
}
