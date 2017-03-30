import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 * 
 * @author MG Hirsch
 *
 */
public class Calendar {
	public ArrayList<Event> events;
	public Map<Integer, String> categories;
	
	/**
	 * Create a new calendar
	 */
	public Calendar() {
		events = new ArrayList<Event>();
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
}
