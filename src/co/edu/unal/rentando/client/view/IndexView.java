package co.edu.unal.rentando.client.view;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.client.presenter.IndexPresenter;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class IndexView extends Composite implements IndexPresenter.Display {

	List<Widget> views;
	int selectedTab;
	TabLayoutPanel tabPanel;

	public IndexView() {
		tabPanel = new TabLayoutPanel(2.5,Unit.EM);
		tabPanel.setAnimationDuration(1000);
	    tabPanel.getElement().getStyle().setMarginBottom(10.0, Unit.PX);
	    tabPanel.ensureDebugId("cwTabPanel");
		// ***********************
	}

	@Override
	public void setUpTab(List<Widget> views, List<String> labels) {
		if (views == null) {
			Window.alert("Views Null");
			views = new ArrayList<Widget>();
			views.add(new Button("No hay nada "));
			labels = new ArrayList<String>();
			labels.add("Error");
		}
		Button btn;
		this.views = views;
		for (int i = 0; i < views.size(); i++) {
			tabPanel.add(views.get(i), labels.get(i));
//			LayoutPanel p = new LayoutPanel();
//			p.setHeight("500px");
//			p.setWidth("1000px");
//			p.getElement().setPropertyString("background", "red");
//			tabPanel.add(p, labels.get(i));			
		}
		if (views.size() > 0 ) {
			setSelectedTab(0);
		}
	}

	public void setSelectedTab(int index) {
		
		tabPanel.selectTab(index);
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return tabPanel.getTabWidget(4);
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
