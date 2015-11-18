package co.edu.unal.rentando.client.custom;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;

public class InputGroup {

	private HorizontalPanel panel;
	private HTML glyphicon;
	private TextBox input;

	private InputGroup() {
		panel = new HorizontalPanel();
		glyphicon = new HTML("");
		input = new TextBox();
		input.getElement().addClassName("form-control");
		panel.getElement().addClassName("input-group");
	}

	public HorizontalPanel getPanel() {
		return panel;
	}

	public void setPanel(HorizontalPanel panel) {
		this.panel = panel;
	}

	public HTML getGlyphicon() {
		return glyphicon;
	}

	public void setGlyphicon(HTML glyphicon) {
		this.glyphicon = glyphicon;
	}

	public TextBox getInput() {
		return input;
	}

	public void setInput(TextBox input) {
		this.input = input;
	}

	public static InputGroup getInputGroup(Glyphicons gly, String placeHolder) {
		InputGroup ingr = new InputGroup();
		ingr.glyphicon.setHTML("<span class='input-group-addon'><span class='"
				+ gly.toString() + "'></span></span>");
		ingr.input.getElement().setAttribute("placeholder", placeHolder);

		ingr.panel.add(ingr.glyphicon);
		ingr.panel.add(ingr.input);
		return ingr;
	}

	public static enum Glyphicons {
		home, user, earphone, phone, pencil, usd, credit_card, envelope, camera, ok, remove, trash;
		public String toString() {
			return "glyphicon glyphicon-" + name().replace("_", "-");
		};
	}

}
