import java.util.ArrayList;
import java.util.Date;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Test");
		
		ArrayList<Event> calendar = new ArrayList<Event>();
		
		Event e = new Event("test", new Date(), new Date(), "description");
		calendar.add(e);
		
		System.out.println("Event: " + e);
		System.out.println("Calendar: " + calendar);
		
	}

}
