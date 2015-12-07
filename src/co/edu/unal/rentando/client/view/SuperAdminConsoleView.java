package co.edu.unal.rentando.client.view;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.client.presenter.SuperAdminConsolePresenter.Display;
import co.edu.unal.rentando.shared.UsrLoginInfo;
import co.edu.unal.rentando.shared.many2many.IUsrLogin.UserRole;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SuperAdminConsoleView extends Composite implements Display {

	private FlowPanel flowPanel;
	private FlexTable table;
	private Button addButton;
	private Button cancelButton;
	private Button saveButton;
	private List<Integer> changed;
	private List<LoginInfoRow> rowInfo;

	public SuperAdminConsoleView() {
		flowPanel = new FlowPanel();
		table = new FlexTable();
		addButton = new Button("Nuevo");
		cancelButton = new Button("Cancelar");
		saveButton = new Button("Guardar");
		changed = new ArrayList<Integer>();
		rowInfo = new ArrayList<SuperAdminConsoleView.LoginInfoRow>();
		// flowPanel.add(addButton);
		flowPanel.add(saveButton);
		flowPanel.add(cancelButton);
		flowPanel.add(table);
		addStyles();
	}

	private void addStyles() {
		table.setTitle("Consola de super administrador");
		HTML headers = new HTML();
		headers.getElement().addClassName("login-table-header");
		headers.setText("Email");
		table.setWidget(0, 0, headers);
		headers = new HTML();
		headers.getElement().addClassName("login-table-header");
		headers.setText("Normal user");
		table.setWidget(0, 1, headers);
		headers = new HTML();
		headers.setText("Admin user");
		headers.getElement().addClassName("login-table-header");
		table.setWidget(0, 2, headers);
		headers = new HTML();
		headers.getElement().addClassName("login-table-header");
		headers.setText("Súper admin");
		table.setWidget(0, 3, headers);

		// table.setText(0, 1, "normal user");
		// table.setText(0, 2, "admin user");
		// table.setText(0, 3, "super admin");
		table.getElement().addClassName("login-table");
	}

	@Override
	public HasClickHandlers getSaveButton() {
		// TODO Auto-generated method stub
		return this.saveButton;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		// TODO Auto-generated method stub
		return this.cancelButton;
	}

	@Override
	public HasClickHandlers addNewUsrLoginInfo() {
		// TODO Auto-generated method stub
		return this.addButton;
	}

	@Override
	public void setLoginList(List<UsrLoginInfo> loginList) {
		rowInfo.clear();
		int next = 0;
		for (int j = 0; j < loginList.size(); j++) {
			next = j + 1;
			UsrLoginInfo login = loginList.get(j);
			LoginInfoRow row = new LoginInfoRow(j, login);
			rowInfo.add(row);
			table.setWidget(next, 0, row.getLabel());
			table.setWidget(next, 1, row.getCheckBox(0));
			table.setWidget(next, 2, row.getCheckBox(1));
			table.setWidget(next, 3, row.getCheckBox(2));
		}
	}

	@Override
	public List<UsrLoginInfo> getListOfChanges() {
		ArrayList<UsrLoginInfo> listOfChanges = new ArrayList<UsrLoginInfo>();
		for (int i = 0; i < changed.size(); i++) {
			listOfChanges.add(rowInfo.get(changed.get(i)).getUsrLoginInfo());
		}
		return listOfChanges;
	}

	@Override
	public void fillTable() {

	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return flowPanel;
	}

	@Override
	public UsrLoginInfo getNewLogin() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addToChanges(int id) {
		if (!changed.contains(id)) {
			changed.add(id);
		}
	}
	
	public void removeFromChanges(int id) {
		if (changed.contains(id)) {
			changed.remove(id);
		}
	}

	class LoginInfoRow extends Composite {
		int id;
		boolean changed = false;
		HTML label;
		CheckBox[] roles;
		HorizontalPanel panel;
		UsrLoginInfo info;

		public LoginInfoRow(int id) {
			this.id = id;
			init();
		}

		public LoginInfoRow(int id, UsrLoginInfo info) {
			this.id = id;
			init();
			setName(info.getId());
			checkRoles(info.getRoles());
		}

		private void init() {
			info = new UsrLoginInfo();
			label = new HTML("Email");
			panel = new HorizontalPanel();
			panel.add(label);
			roles = new CheckBox[3];
			ValueChangeHandler<Boolean> handler = new ValueChangeHandler<Boolean>() {

				@Override
				public void onValueChange(ValueChangeEvent<Boolean> event) {
					boolean check = false;
					for (int i = 0; i < roles.length; i++) {
						check = roles[i].getValue() || check;
					}
					if (check) {
						if (!changed) {
							addToChanges(id);
							changed = true;
						}
					}else{
						Window.alert("El usuario no puede quedar sin rol.");
						removeFromChanges(id);
						changed = false;
					}

				}
			};
			for (int i = 0; i < roles.length; i++) {
				roles[i] = new CheckBox();
				roles[i].addValueChangeHandler(handler);
				panel.add(roles[i]);
			}
			flowPanel.add(panel);
		}

		public void setName(String labelName) {
			label.setText(labelName);
		}

		private void checkRole(int r) {
			roles[r].setValue(true);
		}

		public Widget getLabel() {
			return label;
		}

		public Widget getCheckBox(int r) {
			if (r >= NORMAL_USER && r <= SUPER_ADMIN) {
				return roles[r];
			}
			return null;
		}

		public void checkRoles(List<UserRole> list) {
			for (UserRole role : list) {
				switch (role) {
				case normal_user:
					checkRole(NORMAL_USER);
					break;
				case admin_user:
					checkRole(ADMIN);
					break;
				case super_admin:
					checkRole(SUPER_ADMIN);
					break;

				default:
					break;
				}
			}
		}

		public UsrLoginInfo getUsrLoginInfo() {
			info.setId(label.getText());
			ArrayList<UserRole> list = new ArrayList<UserRole>();
			if (this.roles[NORMAL_USER].getValue())
				list.add(UserRole.normal_user);
			if (this.roles[ADMIN].getValue())
				list.add(UserRole.admin_user);
			if (this.roles[SUPER_ADMIN].getValue())
				list.add(UserRole.super_admin);
			info.setRoles(list);
			return info;
		}

		public Widget getWidget() {
			return panel;
		}

	}

	public static final int NORMAL_USER = 0;
	public static final int ADMIN = 1;
	public static final int SUPER_ADMIN = 2;
	public final int cols = 4;
}
