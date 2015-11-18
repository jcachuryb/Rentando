package co.edu.unal.rentando.client.view;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.client.custom.CarListItem;
import co.edu.unal.rentando.client.custom.InputGroup;
import co.edu.unal.rentando.client.custom.InputGroup.Glyphicons;
import co.edu.unal.rentando.client.presenter.AdminCarPresenter;
import co.edu.unal.rentando.shared.CarInfo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AdminCarView extends Composite implements
		AdminCarPresenter.Display {
	private final VerticalPanel container;
	private final Button saveButton;
	private final Button deleteButton;
	private final Button addButton;
	private final List<CarListItem> carList;
	private CarInfo currentCar;

	public AdminCarView() {
		container = new VerticalPanel();
		saveButton = new Button("Guardar");
		deleteButton = new Button("Borrar");
		addButton = new Button("Añadir");
		carList = new ArrayList<CarListItem>();

		// TODO Auto-generated constructor stub
		addWidgetsAndStyles();
	}

	private void addWidgetsAndStyles() {
		// HorizontalPanel hp = new HorizontalPanel();
		// hp.add(addButton);
		addButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				currentCar = new CarInfo();
				displayCarEditionView();

			}
		});
		container.add(addButton);
		container.add(new HTML("<hr>"));
		

		// TODO Auto-generated method stub

	}


	void displayCarEditionView() {
		CarPopUp popup = new CarPopUp(currentCar);
		popup.show();
	}

	class CarListClickHandler implements ClickHandler {

		private final CarInfo carInfo;

		public CarListClickHandler(CarInfo car) {
			carInfo = car;
		}

		@Override
		public void onClick(ClickEvent event) {
			currentCar = carInfo;
			displayCarEditionView();
		}
	}

	public class CarPopUp extends PopupPanel {
		private CarInfo car;
		private InputGroup id = InputGroup.getInputGroup(Glyphicons.pencil,
				"Placa");
		private InputGroup brand = InputGroup.getInputGroup(Glyphicons.pencil,
				"Marca");
		private InputGroup picture = InputGroup.getInputGroup(
				Glyphicons.camera, "URL imagen");
		private InputGroup ref = InputGroup.getInputGroup(Glyphicons.pencil,
				"Referencia");
		private InputGroup price = InputGroup.getInputGroup(Glyphicons.usd,
				"Precio");
		private TextArea desc = new TextArea();
		private Button cancel = new Button("Cancelar");
		private Image image = new Image();
		private HorizontalPanel mainPanel = new HorizontalPanel();

		public CarPopUp(CarInfo carInfo) {
			super(false);
			this.car = carInfo;
			fillWidget();
			setWidget(mainPanel);
		}

		private void fillWidget() {
			id.getInput().setText(car.getId());
			brand.getInput().setText(car.getBrand());
			price.getInput().setText(car.getPrice());
			picture.getInput().setText(car.getPictURL());
			ref.getInput().setText(car.getReference());
			desc.getElement().setAttribute("placeholder", "Descripción");
			desc.setText(car.getDescription());
			image.setUrl(car.getPictURL());
			image.setHeight("150px");
			image.setWidth("150px");
			cancel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					hide();
				}
			});
			// ----------------------------------------
			HorizontalPanel hp = new HorizontalPanel();
			VerticalPanel vp = new VerticalPanel();
			vp.add(id.getPanel());
			vp.add(picture.getPanel());
			vp.add(brand.getPanel());
			vp.add(ref.getPanel());
			vp.add(price.getPanel());
			mainPanel.add(vp);
			
			vp = new VerticalPanel();
			vp.add(image);
			vp.add(desc);
			mainPanel.add(vp);
			hp.add(cancel);
			hp.add(deleteButton);
			hp.add(saveButton);
			mainPanel.add(hp);

		}

	}

	@Override
	public CarInfo getCurrentcar() {
		// TODO Auto-generated method stub
		return currentCar;
	}

	@Override
	public HasClickHandlers getSaveButton() {
		// TODO Auto-generated method stub
		return saveButton;
	}

	@Override
	public HasClickHandlers getDeleteButton() {
		// TODO Auto-generated method stub
		return deleteButton;
	}

	@Override
	public void fillCarList(List<CarInfo> list) {
		for (CarInfo carInfo : list) {
			CarListItem item = new CarListItem();
			item.fillCarInfo(carInfo);
			item.setClickHandler(new CarListClickHandler(carInfo));
			carList.add(item);
			container.add(item.getWidget());
		}
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return container;
	}
}
