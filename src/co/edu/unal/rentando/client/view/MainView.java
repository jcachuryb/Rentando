package co.edu.unal.rentando.client.view;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.client.presenter.MainBarPresenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class MainView extends Composite implements MainBarPresenter.Display {

	public final Anchor imgAnchor = new Anchor();
	public final List<MenuBarItem> mainMenu = new ArrayList<>();;
	public final HorizontalPanel bar = new HorizontalPanel();

	public MainView() {
		mainMenu.add(new MenuBarItem(MenuItemType.INICIO));
		mainMenu.add(new MenuBarItem(MenuItemType.ENTRAR));
		addStyles();
	}

	public MainView(List<MenuItemType> items) {
		// TODO Auto-generated constructor stub
		for (MenuItemType menuBarItem : items) {
			mainMenu.add(new MenuBarItem(menuBarItem));
		}
		addStyles();
	}

	private void addStyles() {
		// TODO Auto-generated method stub
		
	}


	class MenuBarItem {
		private Anchor link;
		private MenuItemType type;

		public MenuBarItem() {
			// TODO Auto-generated constructor stub
		}

		public MenuBarItem(MenuItemType type) {
			this.link = new Anchor(type.toString());
			this.type = type;
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

	public static enum MenuItemType {
		INICIO, PERFIL, EXTRA, USUARIOS, SALIR, ENTRAR;
	}

	private Anchor lookForItem(MenuItemType item){
		for (MenuBarItem elem: this.mainMenu) {
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
		return lookForItem(MenuItemType.SALIR);
	}

	@Override
	public HasClickHandlers getExtraInfoButton() {
		// TODO Auto-generated method stub
		return lookForItem(MenuItemType.EXTRA);
	}

}
