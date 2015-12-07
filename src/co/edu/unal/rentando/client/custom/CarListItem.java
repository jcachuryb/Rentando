package co.edu.unal.rentando.client.custom;

import co.edu.unal.rentando.shared.CarInfo;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CarListItem {
	private final FocusPanel wrapper = new FocusPanel();
	private VerticalPanel r1 = new VerticalPanel();
	private Image image = new Image();
	private HTML brand = new HTML();
	private HTML ref = new HTML();
	private HTML description = new HTML();
	private HTML price = new HTML();

	private final HorizontalPanel panel = new HorizontalPanel();

	public CarListItem() {
//		panel.getElement().setClassName("xs-col-12");
		image.getElement().addClassName("list-car-image");
		brand.getElement().addClassName("list-car-brand");
		ref.getElement().addClassName("list-car-reference");
		price.getElement().addClassName("list-car-price");
		description.getElement().addClassName("list-car-description");
		wrapper.getElement().addClassName("list-car-panel");
		
		
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public HTML getTitle() {
		return brand;
	}

	public void setTitle(HTML title) {
		this.brand = title;
	}

	public HTML getDescription() {
		return description;
	}

	public void setDescription(HTML description) {
		this.description = description;
	}

	public HTML getPrice() {
		return price;
	}

	public void setPrice(HTML price) {
		this.price = price;
	}

	public void setClickHandler(ClickHandler handler){
		wrapper.addClickHandler(handler);
	}

	public void fillCarInfo(CarInfo carInfo) {
		image.setUrl(carInfo.getPictURL());
		brand.setHTML(carInfo.getBrand());
		ref.setHTML(carInfo.getReference());
		description.setHTML("<p>" + carInfo.getDescription() + "</p>");
		price.setHTML(carInfo.getPrice());
	}

	public Widget getWidget() {

		r1.add(brand);
		r1.add(ref);
		r1.add(image);
		panel.add(r1);
		r1 = new VerticalPanel();
		r1.getElement().addClassName("list-vert-panel");
		r1.add(new HTML("<span style='font-size: 14pt; color: #000, font-weight: bold;'>Sobre este auto: </span>"));
		r1.add(description);
		r1.add(price);
		panel.add(r1);
		wrapper.add(panel);

		return wrapper;
	}

}
