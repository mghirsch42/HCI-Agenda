import java.util.ArrayList;

public class Calendar {
	public ArrayList<Event> events;
	
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
		return events;
	}
	
	public ArrayList<Event> sortByCategory() {
		// sort events by category
		return events;
	}
}
