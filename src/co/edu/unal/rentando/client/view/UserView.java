package co.edu.unal.rentando.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.unal.rentando.client.behavior.LoadCarRentals;
import co.edu.unal.rentando.client.custom.CarListItem;
import co.edu.unal.rentando.client.custom.RPopUp;
import co.edu.unal.rentando.client.custom.RentCalendar;
import co.edu.unal.rentando.client.presenter.UserPresenter;
import co.edu.unal.rentando.shared.CarInfo;
import co.edu.unal.rentando.shared.RentInfo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.googlecode.objectify.condition.IfZero;

public class UserView extends CarListView implements UserPresenter.Display {

	FlowPanel mainPanel;
	Button rentButton;
	private CarPopUp popup;
	ScrollPanel carListContainer;
	List<RentInfo> rentals;
	LoadCarRentals observer;
	DateBox initDate, dueDate;
	RentInfo userRent;
	FlowPanel msg;

	public UserView() {
		mainPanel = new FlowPanel();
		rentButton = new Button("Rentar");
		msg = new FlowPanel();
		msg.getElement().addClassName("Rent-info");
		mainPanel.add(msg);
		mainPanel.add(super.asWidget());
	}

	@Override
	public void transactionDone() {
		observer.loadUserRental();
		if (popup != null) {
			popup.hide();
			popup = null;
		}
	}

	@Override
	public void displaySingleCarView() {
		if (popup == null || !popup.isVisible()) {
			observer.loadRentals(getCurrentcar().getId());
			popup = new CarPopUp();
			popup.show();
		}
	}

	@Override
	public void setRentInfo(RentInfo rent) {
		userRent = rent;
		HTML message = new HTML();
		msg.clear();
		if (rent.getId() != "") {
			rentButton.setVisible(false);
			DateTimeFormat format;
			format = DateTimeFormat.getFormat(PredefinedFormat.DATE_LONG);
			message.setHTML("<p class='rent-message active-rent'>Tienes un auto <strong>"
					+ rent.getCar().getBrand()
					+ " - "
					+ rent.getCar().getReference()
					+ "</strong> rentado.<br>"
					+ "La renta va desde <strong>"
					+ format.format(rent.getInitDate())
					+ "</strong> hasta <strong>" + format.format(rent.getDueDate()) + "</strong>.</p>");
		} else {
			rentButton.setVisible(true);
			message.setHTML("<p class='rent-message none-rent'><strong>No tienes una renta vigente.</strong><br>"
					+ " Te invitamos a que rentes uno de nuestros autos.</p>");
		}
		msg.add(message);

	}

	@Override
	public RentInfo getRenInfo() {
		RentInfo rent = new RentInfo();
		rent.setInitDate(initDate.getValue());
		rent.setDueDate(dueDate.getValue());
		rent.setCar(popup.getCar());
		return rent;
	}

	@Override
	public HasClickHandlers getRentButton() {
		// TODO Auto-generated method stub
		return rentButton;
	}

	@Override
	public CarListView getSuperView() {
		return this;
	}

	@Override
	public void loadCurrentCarRentals(List<RentInfo> list) {
		rentals = list;
		if (popup != null) {
			popup.addCalendarInfo();
		}
	}

	@Override
	public void setRentLoader(LoadCarRentals loader) {
		observer = loader;
	}

	@Override
	public Widget asWidget() {
		return this.mainPanel;
	}

	public class CarPopUp extends RPopUp {
		CarInfo car;
		CarListItem item;
		DatePicker calendar;
		Date prevInit, prevDue;

		Button cancelBtn = new Button("Cancelar");

		FlowPanel datePickers = new FlowPanel();
		FlowPanel buttons = new FlowPanel();

		public CarPopUp() {
			super("Deseo rentar...");
			prevInit = null;
			prevDue = null;
			calendar = new DatePicker();
			initDate = new DateBox();
			dueDate = new DateBox();
			dueDate.setEnabled(false);
			initDate.getElement().setAttribute("placeholder", "Fecha inicio");
			dueDate.getElement().setAttribute("placeholder", "Fecha fin");
			car = getCurrentcar();
			item = new CarListItem();
			rentButton.setEnabled(false);

			item.fillCarInfo(car);
			// ***************************
			datePickers.add(initDate);
			datePickers.add(dueDate);
			buttons.add(cancelBtn);
			buttons.add(rentButton);
			// ***************************
			getContainer().add(item.getWidget());
			getContainer().add(calendar);
			getContainer().add(datePickers);
			getContainer().add(buttons);
			addDateBoxEvent();
			addStyles();
		}

		public CarInfo getCar() {
			// TODO Auto-generated method stub
			return car;
		}

		private void addDateBoxEvent() {
			initDate.addValueChangeHandler(new ValueChangeHandler<Date>() {

				@Override
				public void onValueChange(ValueChangeEvent<Date> event) {
					Date date = event.getValue();
					String style = calendar.getStyleOfDate(date) != null ? calendar
							.getStyleOfDate(date) : "";

					if (!date.before(new Date()) && !style.contains(BUSY_DATE)) {
						if (prevInit != null) {
							if (prevDue != null) {
								if (date.after(prevInit)
										&& !date.after(prevDue)) {
									unPaintDateInterval(prevDue, date,
											SELECTED_DATE);
									calendar.addStyleToDates(SELECTED_DATE,
											date);
								}
							} else {
								calendar.removeStyleFromDates(SELECTED_DATE,
										prevInit);
								prevInit = date;
								calendar.addStyleToDates(SELECTED_DATE, date);
								dueDate.setEnabled(true);
							}
						} else {
							prevInit = date;
							calendar.addStyleToDates(SELECTED_DATE, date);
							dueDate.setEnabled(true);
						}
					} else {
						Window.alert("Intente con otra fecha");
					}
					initDate.setValue(prevInit);
				}
			});

			dueDate.addValueChangeHandler(new ValueChangeHandler<Date>() {

				@Override
				public void onValueChange(ValueChangeEvent<Date> event) {
					final Date date = event.getValue();
					String style = calendar.getStyleOfDate(date) != null ? calendar
							.getStyleOfDate(date) : "";
					if (!date.before(prevInit) && !style.contains(BUSY_DATE)
							&& checkMiddleDates(prevInit, date, BUSY_DATE)) {
						if (prevDue != null) {
							if (date.after(prevDue)) {
								if (checkMiddleDates(prevDue, date, BUSY_DATE)) {
									paintMiddleDate(prevDue, date,
											SELECTED_DATE);
								} else {
									Window.alert("Intente con otra fecha");
								}
							} else {
								// Window.alert("NO está después");
								if (date.before(prevDue)) {
									Window.alert("Despintar from "
											+ date.toString() + " to "
											+ prevDue.toString());
									unPaintDateInterval(date, prevDue,
											SELECTED_DATE);
									calendar.addStyleToDates(SELECTED_DATE,
											date);
								}
							}
						} else {
							// Window.alert("Pintar from " + prevInit.toString()
							// + " to " + date.toString());

							paintMiddleDate(prevInit, date, SELECTED_DATE);
						}
						prevDue = date;
						rentButton.setEnabled(true);
					} else {
						if (dueDate == null) {
							rentButton.setEnabled(false);
						}
					}
					dueDate.setValue(prevDue);
				}

			});
		}

		private void addStyles() {
			datePickers.getElement().addClassName("pop-up");
			// buttons.getElement().addClassName("");
			//
			// initDate.getElement().addClassName("");
			// dueDate.getElement().addClassName("");
			// rentButton.getElement().addClassName("");
			// cancelBtn.getElement().addClassName("");

			cancelBtn.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					transactionDone();
				}
			});
		}

		private void addCalendarInfo() {
			if (rentals != null) {
				for (RentInfo info : rentals) {
					paintMiddleDate(info.getInitDate(), info.getDueDate(),
							RentCalendar.BUSY_DATE);
				}
			}

		}

		public void unPaintDateInterval(Date from, Date to, String cssClass) {
			calendar.removeStyleFromDates(cssClass, from, to);
			if (to.after(from)) {
				Date date = (Date) from.clone();
				String style = "";
				do {
					CalendarUtil.addDaysToDate(date, 1);
					style = calendar.getStyleOfDate(date) != null ? calendar
							.getStyleOfDate(date) : "";
					Window.alert(style);
					// calendar.removeStyleFromDates(cssClass, date);
				} while (date.before(to));
			}
		}

		public void paintMiddleDate(Date from, Date to, String cssClass) {
			if (to.after(from)) {
				calendar.addStyleToDates(cssClass, from, to);
				do {
					CalendarUtil.addDaysToDate(from, 1);
					calendar.addStyleToDates(cssClass, from);
				} while (!from.equals(to));
			}
		}

		public boolean checkMiddleDates(Date from, Date to, String warning) {
			if (to.after(from)) {
				String style = "";
				Date date = (Date) from.clone();
				do {
					CalendarUtil.addDaysToDate(date, 1);
					style = calendar.getStyleOfDate(date) != null ? calendar
							.getStyleOfDate(date) : "";
					if (style.contains(BUSY_DATE)) {
						return false;
					}
				} while (date.before(to));
			}
			return true;
		}

		public static final String BUSY_DATE = "datePickerDayBusy";
		public static final String SELECTED_DATE = "datePickerDaySelected";
	}

}
