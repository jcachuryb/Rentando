package co.edu.unal.rentando.client.view;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.client.AppController;
import co.edu.unal.rentando.client.behavior.RoleChange;
import co.edu.unal.rentando.client.presenter.MainBarPresenter;
import co.edu.unal.rentando.client.presenter.MainBarPresenter.MenuItemType;
import co.edu.unal.rentando.shared.many2many.IUsrLogin.UserRole;

import com.google.gwt.aria.client.Role;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainView extends Composite implements MainBarPresenter.Display, RoleChange  {

	public HTML appName;
	public List<MenuBarItem> mainMenu = new ArrayList<>();;
	public HorizontalPanel bar = new HorizontalPanel();
	public HorizontalPanel menuList = new HorizontalPanel();
	private Anchor selected;

	public MainView() {
		mainMenu.add(new MenuBarItem(MenuItemType.ENTRAR));
		mainMenu.add(new MenuBarItem(MenuItemType.INICIO));
		mainMenu.add(new MenuBarItem(MenuItemType.PERFIL));
		mainMenu.add(new MenuBarItem(MenuItemType.USUARIOS));
		mainMenu.add(new MenuBarItem(MenuItemType.ADMIN));
		mainMenu.add(new MenuBarItem(MenuItemType.SUPERADMIN));
		mainMenu.add(new MenuBarItem(MenuItemType.SALIR));

		selected = lookForItem(MenuItemType.INICIO);
		addStyles();
		bar.add(appName);
		updateMenuList();
		bar.add(menuList);
	}

	private void updateMenuList() {
		menuList.clear();
		for (MenuBarItem listElement : this.mainMenu) {
			menuList.add(listElement.link);
		}
	}

	private void addStyles() {
		bar.getElement().addClassName("main-bar");
		appName = new HTML(
				"<span style='font-size: 20pt;margin: 8px;font-weight: 700;position: relative;top: 6px;'><span style='color:#fff'>RENT</span><span style='color:#F4EB49'>ANDO</span></span>");
		menuList.getElement().addClassName("mnu-list");

	}

	class MenuBarItem {
		private Anchor link;
		private MenuItemType type;
		private boolean visibility = false;

		public MenuBarItem() {
			// TODO Auto-generated constructor stub
		}

		public MenuBarItem(MenuItemType type) {
			this.type = type;
			this.link = new Anchor(type.toString());
			this.link.getElement().addClassName("mnu-li-item");

		}

		public Anchor getLink() {
			return link;
		}

		public void setLink(Anchor link) {
			this.link = link;
		}

		public MenuItemType getType() {
			return type;
		}

		public void setType(MenuItemType type) {
			this.type = type;
		}

		public boolean isVisibility() {
			return visibility;
		}

		public void setVisibility(boolean visibility) {
			this.visibility = visibility;
			link.setVisible(visibility);
		}

	}

	@Override
	public void setSelected(MenuItemType item) {
		selected.getElement().setAttribute("style", "color:#fff !important");
		selected = lookForItem(item);
		selected.getElement()
				.setAttribute("style", "color: #F4EB49 !important");
	}

	private Anchor lookForItem(MenuItemType item) {
		for (MenuBarItem elem : this.mainMenu) {
			if (elem.getType().equals(item)) {
				return elem.link;
			}
		}
		return null;
	}

	@Override
	public HasClickHandlers getHomeButton() {
		// TODO Auto-generated method stub
		return lookForItem(MenuItemType.INICIO);
	}

	@Override
	public HasClickHandlers getLogoutButton() {
		// TODO Auto-generated method stub
		return lookForItem(MenuItemType.SALIR);
	}

	@Override
	public HasClickHandlers getProfileButton() {
		// TODO Auto-generated method stub
		return lookForItem(MenuItemType.PERFIL);
	}

	@Override
	public HasClickHandlers getUsersButton() {
		// TODO Auto-generated method stub
		return lookForItem(MenuItemType.USUARIOS);
	}

	@Override
	public HasClickHandlers getLoginButton() {
		// TODO Auto-generated method stub
		return lookForItem(MenuItemType.ENTRAR);
	}

	@Override
	public HasClickHandlers getAdminButton() {
		// TODO Auto-generated method stub
		return lookForItem(MenuItemType.ADMIN);
	}
	
	@Override
	public HasClickHandlers getSuperAdminButton() {
		// TODO Auto-generated method stub
		return lookForItem(MenuItemType.SUPERADMIN);
	}
	
	@Override
	public Widget asWidget() {
		return bar;
	}

	@Override
	public Anchor getLoginLink() {
		// TODO Auto-generated method stub
		return lookForItem(MenuItemType.ENTRAR);
	}

	@Override
	public Anchor getLogoutLink() {
		// TODO Auto-generated method stub
		return lookForItem(MenuItemType.SALIR);
	}

	@Override
	public void updateMenuBarList(List<UserRole> roles) {
		if (roles.contains(UserRole.outside_user)) {
			hideMenuItem(MenuItemType.INICIO);
			hideMenuItem(MenuItemType.USUARIOS);
			hideMenuItem(MenuItemType.SALIR);
			hideMenuItem(MenuItemType.PERFIL);
			hideMenuItem(MenuItemType.ADMIN);
			hideMenuItem(MenuItemType.SUPERADMIN);
			showMenuItem(MenuItemType.ENTRAR);
			return;
		}
		hideMenuItem(MenuItemType.ENTRAR);
		showMenuItem(MenuItemType.PERFIL);
		if (roles.contains(UserRole.normal_user)) {
			showMenuItem(MenuItemType.INICIO);
		}
		if (roles.contains(UserRole.admin_user)) {
			showMenuItem(MenuItemType.USUARIOS);
			showMenuItem(MenuItemType.ADMIN);
		}
		if (roles.contains(UserRole.super_admin)) {
			showMenuItem(MenuItemType.SUPERADMIN);
		}
		showMenuItem(MenuItemType.SALIR);
	}

	private void hideMenuItem(MenuItemType type) {
		setMenuItemVisibility(type, false);
	}

	private void showMenuItem(MenuItemType type) {
		setMenuItemVisibility(type, true);
	}

	private void setMenuItemVisibility(MenuItemType type, boolean val) {
		lookForItem(type).setVisible(val);
	}

	@Override
	public void onRoleChange(List<UserRole> roles) {
		updateMenuBarList(roles);
	}

}
