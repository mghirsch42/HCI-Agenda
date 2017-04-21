package model;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;


/**
 * 
 * @author MG Hirsch
 *
 */
public class MyCalendar {
	public GregorianCalendar calendar;
	public ArrayList<Event> events;
	public Map<Integer, String> categories;
	
	/**
	 * Create a new calendar
	 */
	public MyCalendar() {
		events = new ArrayList<Event>();
		calendar = new GregorianCalendar();
	}
	
	/**
	 * 
	 * @param e	the event to add
	 * @return	boolean true if successful add, false if not successful
	 */
	public boolean addEvent(Event e) {
		return events.add(e);
	}
	
	/**
	 * 
	 */
	public String toString() {
		return events.toString();
	}
	
	/**
	 * 
	 * @return the array list of events
	 */
	public ArrayList<Event> getEvents() {
		return events;
	}
	
	public ArrayList<Event> getEvents(GregorianCalendar start, GregorianCalendar end){
		Collections.sort(events, new DateCompare());
		ArrayList<Event> result = new ArrayList<Event>();
		// simple search and grab algorithm - can be optimized by using the sorted list
		for(Event e : events) {
			if(e.getStart().after(start) || e.getEnd().before(end)) {
				result.add(e);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @return	the array list of events, sorted by category
	 */
	public ArrayList<Event> sortByStart() {
		// sort events by date
		Collections.sort(events, new DateCompare());
		
		return events;
	}
	
	/**
	 * 
	 * @return	the array list of events, sorted by category
	 */
	public ArrayList<Event> sortByCategory() {
		// sort events by category
		return events;
	}

	public GregorianCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(GregorianCalendar calendar) {
		this.calendar = calendar;
	}
	

}
