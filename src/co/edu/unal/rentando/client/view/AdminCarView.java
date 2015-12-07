package co.edu.unal.rentando.client.view;

import co.edu.unal.rentando.client.custom.InputGroup;
import co.edu.unal.rentando.client.custom.InputGroup.Glyphicons;
import co.edu.unal.rentando.client.presenter.AdminCarPresenter;
import co.edu.unal.rentando.shared.CarInfo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AdminCarView extends CarListView implements
		AdminCarPresenter.Display {
	FlowPanel mainPanel;
	private final Button saveButton;
	private final Button deleteButton;
	private final Button addButton;
	private CarPopUp popup;

	public AdminCarView() {
		mainPanel = new FlowPanel();
		saveButton = new Button("Guardar");
		deleteButton = new Button("Borrar");
		addButton = new Button("Añadir");
		
		// TODO Auto-generated constructor stub
		addWidgetsAndStyles();
		mainPanel.add(addButton);
		mainPanel.add(super.asWidget());
	}

	private void addWidgetsAndStyles() {
		addButton.getElement().addClassName("btn-add-car");
		addButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setCurrentCar(new CarInfo());
				displaySingleCarView();
			}
		});

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
			this.car.setIsNew(car.getId() == "" ? true : false);
			this.id.getInput().setEnabled(car.isNew());
			fillWidget();
			setWidget(mainPanel);
			addStyles();
		}

		private void addStyles() {
			image.getElement().setClassName("list-car-image");
			desc.getElement().addClassName("edition-car-description");
			
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
					transactionDone();

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
			hp.add(image);
			hp.add(desc);
			vp.add(hp);
			hp = new HorizontalPanel();
			hp.add(cancel);
			hp.add(deleteButton);
			hp.add(saveButton);
			vp.add(hp);
			mainPanel.add(vp);
			

		}

		public void updateCurrentCar() {
			car.setId(id.getInput().getText());
			car.setBrand(brand.getInput().getText());
			car.setPictURL(picture.getInput().getText());
			car.setDescription(desc.getText());
			car.setPrice(price.getInput().getText());
			car.setReference(ref.getInput().getText());
			setCurrentCar(car);
		}

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
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return mainPanel;
	}

	@Override
	public void updateCurrentCar() {
		popup.updateCurrentCar();
	}

	@Override
	public void transactionDone() {
		if (popup != null) {
			popup.hide();
			popup = null;
		}

	}

	@Override
	public void displaySingleCarView() {
		if (popup == null || !popup.isVisible()) {
			popup = new CarPopUp(getCurrentcar());
			popup.show();
		}
	}

	@Override
	public CarListView getSuperView() {
		// TODO Auto-generated method stub
		return this;
	}
}
