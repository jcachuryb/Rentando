package co.edu.unal.rentando.client.view;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.client.presenter.MainBarPresenter;
import co.edu.unal.rentando.client.presenter.MainBarPresenter.MenuItemLists;
import co.edu.unal.rentando.client.presenter.MainBarPresenter.MenuItemType;

import com.google.gwt.dev.asm.Label;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainView extends Composite implements MainBarPresenter.Display {

	public HTML appName;
	public List<MenuBarItem> mainMenu = new ArrayList<>();;
	public HorizontalPanel bar = new HorizontalPanel();
	public HorizontalPanel menuList = new HorizontalPanel();
	private final boolean doLogin;
	private Anchor selected;

	public MainView(MenuItemLists list) {
		mainMenu.clear();
		mainMenu.add(new MenuBarItem(MenuItemType.INICIO));
		switch (list) {
		case normalUser:
			mainMenu.add(new MenuBarItem(MenuItemType.PERFIL));
			mainMenu.add(new MenuBarItem(MenuItemType.EXTRA));
			mainMenu.add(new MenuBarItem(MenuItemType.SALIR));
			doLogin = false;
			break;
		case adminUser:
			mainMenu.add(new MenuBarItem(MenuItemType.PERFIL));
			mainMenu.add(new MenuBarItem(MenuItemType.USUARIOS));
			mainMenu.add(new MenuBarItem(MenuItemType.SALIR));
			doLogin = false;
			break;

		default:
			mainMenu.add(new MenuBarItem(MenuItemType.ENTRAR));
			doLogin = true;
			break;
		}
		selected = lookForItem(MenuItemType.INICIO);
		addStyles();
		bar.add(appName);
		for (MenuBarItem listElement : this.mainMenu) {
			menuList.add(listElement.link);
		}
		bar.add(menuList);
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

	}
	@Override
	public void setSelected(MenuItemType item){
		selected.getElement().removeClassName("mnu-selected");
		selected = lookForItem(item);
		selected.getElement().setClassName("mnu-selected");
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
	public HasClickHandlers getExtraInfoButton() {
		// TODO Auto-generated method stub
		return lookForItem(MenuItemType.EXTRA);
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
	public boolean doLogin() {
		// TODO Auto-generated method stub
		return doLogin;
	}

}
