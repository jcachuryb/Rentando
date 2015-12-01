package co.edu.unal.rentando.client.presenter;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.client.RentandoServiceAsync;

import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class IndexPresenter extends Presenter implements IPresenter {
	public interface Display {

		void setUpTab(List<Widget> views, List<String> labels);

		Widget asWidget();
	}

	private Display display;
	private MainBarPresenter mbPresenter;
	List<String> labels;
	List<IPresenter> presenters;

	public IndexPresenter(RentandoServiceAsync rpcService,
			HandlerManager eventBus, Display view, List<IPresenter> presenters,
			List<String> labels) {
		
		super(rpcService, eventBus);
		display = view;
		this.presenters = presenters;
		this.labels = labels;
		mbPresenter = MainBarPresenter.getInstance();
	}

	@Override
	public void go(HasWidgets container) {
		List<Widget> views = new ArrayList<>();
		for (IPresenter pr : presenters) {
			pr.go(new FlowPanel());
			views.add(pr.getMainWidget());
		}
		display.setUpTab(views, labels);
		mbPresenter.go(container);
		FlowPanel panel = new FlowPanel();
		panel.add(display.asWidget());
		container.add(panel);
	}

	@Override
	public Widget getMainWidget() {
		// TODO Auto-generated method stub
		return display.asWidget();
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}
}
