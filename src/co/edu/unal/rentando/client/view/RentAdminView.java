package co.edu.unal.rentando.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

import co.edu.unal.rentando.client.behavior.ReturnCar;
import co.edu.unal.rentando.client.custom.CarListItem;
import co.edu.unal.rentando.client.custom.RPopUp;
import co.edu.unal.rentando.client.presenter.RentAdminPresenter.Display;
import co.edu.unal.rentando.shared.CarInfo;
import co.edu.unal.rentando.shared.RentInfo;

public class RentAdminView implements Display {
	private FlowPanel flowPanel;
	private FlexTable table;
	private Button registerButton;
	private List<RentInfo> list = new ArrayList<RentInfo>();
	ScrollPanel container;
	private RentInfo currentRent;
	ReturnCar admin;

	public RentAdminView() {
		table = new FlexTable();
		table.getElement().addClassName("rental-list");
		container = new ScrollPanel();
		registerButton = new Button();
		container.add(table);
		startTable();
	}

	public void startTable() {
		HTML headers = new HTML();
		headers.getElement().addClassName("login-table-header");
		headers.setText("Cliente");
		table.setWidget(0, 0, headers);
		headers = new HTML();
		headers.getElement().addClassName("login-table-header");
		headers.setText("Fecha Inicio");
		table.setWidget(0, 1, headers);
		headers = new HTML();
		headers.getElement().addClassName("login-table-header");
		headers.setText("Fecha Fin");
		table.setWidget(0, 2, headers);
	}

	@Override
	public HasClickHandlers getSaveButton() {
		// TODO Auto-generated method stub
		return registerButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRentalsList(List<RentInfo> list) {
		this.setList(list);
	}

	@Override
	public void fillTable() {
		DateTimeFormat format;
		format = DateTimeFormat.getFormat(PredefinedFormat.DATE_LONG);
		int next = 1;
		for (final RentInfo info : list) {
			Button btn = new Button("Registrar Devolución");
			btn.addClickHandler(new MyHandler(info, next));

			table.setHTML(next, 0, info.getId());
			table.setHTML(next, 1, format.format(info.getInitDate()));
			table.setHTML(next, 2, format.format(info.getDueDate()));
			table.setWidget(next, 3, btn);
			next++;
		}
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return container;
	}

	@Override
	public RentInfo getCurrentRent() {
		return currentRent;
	}

	public void setCurrentRent(RentInfo currentRent) {
		this.currentRent = currentRent;
	}

	public List<RentInfo> getList() {
		return list;
	}

	public void setList(List<RentInfo> list) {
		this.list = list;
	}

	class RentalInfoPopup extends RPopUp {
		private CarInfo car;
		private CarListItem item;
		private RentInfo info;

		public RentalInfoPopup(RentInfo rent) {
			info = rent;
		}

	}

	class MyHandler implements ClickHandler {
		private RentInfo info;
		private int row;

		public MyHandler(RentInfo info, int r) {
			this.info = info;
			this.row = r;
		}

		@Override
		public void onClick(ClickEvent event) {
			setCurrentRent(info);
			table.removeRow(row);
			list.remove(row - 1);
			admin.returnCar();
		}

	}

	@Override
	public void setRentAdmin(ReturnCar admin) {
		this.admin = admin;
	}

}
