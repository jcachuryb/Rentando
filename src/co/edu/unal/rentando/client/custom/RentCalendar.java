package co.edu.unal.rentando.client.custom;

import java.util.Date;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.user.datepicker.client.DatePicker;

public class RentCalendar {

	public static final String BUSY_DATE = "datePickerDayBusy";
	public static final String SELECTED_DATE = "datePickerDaySelected";
	private DatePicker calendar;
	private Date prevInit, prevDue;

	public RentCalendar() {
		this.calendar = new DatePicker();
		prevInit = null;
		prevDue = null;
	}

	public DatePicker getCalendar() {
		return calendar;
	}

	public Date getPrevInit() {
		return prevInit;
	}

	public void setPrevInit(Date date) {
//		if (prevInit != null && prevDue != null) {
//			if (date.after(prevInit)) {
//				unPaintDateInterval(prevInit, date, SELECTED_DATE);
//			} else {
//				if (date.before(prevInit)) {
//					paintMiddleDate(date, prevDue, SELECTED_DATE);
//				}
//			}
//		}
			
//		if (prevInit != null && prevDue != null){
//			if ( date.after(prevInit)) {
//				unPaintDateInterval(prevInit, date, SELECTED_DATE);
//			}else{
//				unPaintDateInterval(prevInit, prevDue, SELECTED_DATE);
//			}
//		}else{
//			
//		}
//		Window.alert(date.toString());
//		addStyleToDates(SELECTED_DATE, date);
//		this.prevInit = date;
	}

	public Date getPrevDue() {
		return prevDue;
	}

	public void setPrevDue(Date date) {
		if (prevDue != null) {
			if (prevDue.before(date)) {
				paintMiddleDate(prevDue, date, SELECTED_DATE);
			} else {
				if (prevDue.after(date)) {
					unPaintDateInterval(date, prevDue, SELECTED_DATE);
					calendar.addStyleToDates(SELECTED_DATE, date);
				}
			}
		} else {
			paintMiddleDate(prevInit, date, SELECTED_DATE);
		}
		this.prevDue = date;
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

	public void unPaintDateInterval(Date from, Date to, String cssClass) {
		calendar.removeStyleFromDates(cssClass, from, to);
		if (to.after(from)) {
			do {
				CalendarUtil.addDaysToDate(from, 1);
				calendar.removeStyleFromDates(cssClass, from);
			} while (from.before(to));
		}
	}

	public void addStyleToDates(String style, Date date) {
		calendar.addStyleToDates(style, date);
	}

	public void changeInterval(Date init, Date due) {
		if (prevInit == null) {

		}
	}
}
