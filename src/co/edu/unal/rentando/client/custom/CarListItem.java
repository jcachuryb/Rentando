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
	private HTML title = new HTML();
	private HTML description = new HTML();
	private HTML price = new HTML();

	private final HorizontalPanel panel = new HorizontalPanel();

	public CarListItem() {
//		panel.getElement().setClassName("xs-col-12");
		image.getElement().addClassName("list-car-image");
//		title.getElement().addClassName("");
//		price.getElement().addClassName("");
//		description.getElement().addClassName("");
//		wrapper.getElement().addClassName("");
		
		
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public HTML getTitle() {
		return title;
	}

	public void setTitle(HTML title) {
		this.title = title;
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
		title.setHTML("<strong>" + carInfo.getBrand() + "<br> "
				+ carInfo.getReference() + "</strong>");
		description.setHTML("<p class='li-car-desc'>" + carInfo.getDescription() + "</p>");
		price.setHTML("<strong>Precio por día:&nbsp;</strong>"
				+ carInfo.getPrice());
	}

	public Widget getWidget() {

		r1.add(title);
		r1.add(image);
		panel.add(r1);
		r1 = new VerticalPanel();
		r1.add(new HTML("<strong>Sobre este auto: </strong>"));
		r1.add(description);
		r1.add(price);
		panel.add(r1);
		wrapper.add(panel);

		return wrapper;
	}

}
