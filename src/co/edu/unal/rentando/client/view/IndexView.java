package co.edu.unal.rentando.client.view;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.client.presenter.IndexPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class IndexView extends Composite implements IndexPresenter.Display {

	List<Button> tabButtons;
	HorizontalPanel tabMenu;
	HorizontalPanel pageHolder;
	VerticalPanel container;
	List<Widget> views;
	int selectedTab;

	public IndexView() {
		tabMenu = new HorizontalPanel();
		pageHolder = new HorizontalPanel();
		container = new VerticalPanel();
		tabButtons = new ArrayList<>();
		// ***********************
		
		container.add(tabMenu);
		container.add(pageHolder);
	}

	@Override
	public void setUpTab(List<Widget> views, List<String> labels) {
		if (views == null) {
			return;
		}
		Button btn;
		this.views = views;
		for (int i = 0; i < views.size(); i++) {
			btn = new Button(labels.get(i));
			btn.addClickHandler(new TabClickListener(i));
			btn.getElement().addClassName("tab-btn");
			tabButtons.add(btn);
			tabMenu.add(btn);
		}
		if (views.size() > 0 ) {
			setSelectedTab(0);
		}
	}

	public void setSelectedTab(int index) {
//		if (index == selectedTab) {
//			return;
//		}
		tabButtons.get(selectedTab).getElement().removeClassName("tab-btn.selected");
		this.selectedTab = index;
		tabButtons.get(selectedTab).getElement().addClassName("tab-btn.selected");
		showCurrentView();
	}

	private void showCurrentView() {
		pageHolder.clear();
		pageHolder.add(views.get(selectedTab));
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return container;
	}

	class TabClickListener implements ClickHandler {
		final int index;

		TabClickListener(int i) {
			this.index = i;
		}

		@Override
		public void onClick(ClickEvent event) {
			Window.alert("click");
			setSelectedTab(index);

		}

	}
}
