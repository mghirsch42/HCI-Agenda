import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class Calendar {
	public ArrayList<Event> events;
	public Map<Integer, String> categories;
	
	public Calendar() {
		events = new ArrayList<Event>();
	}
	
	public boolean addEvent(Event e) {
		return events.add(e);
	}
	
	public String toString() {
		return events.toString();
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
	
	public ArrayList<Event> sortByStart() {
		// sort events by date
		DateCompare dc = new DateCompare<Event>();
		//Collections.sort(events, new DateCompare<Event>());
		
		return events;
	}
	
	public ArrayList<Event> sortByCategory() {
		// sort events by category
		return events;
	}
}
