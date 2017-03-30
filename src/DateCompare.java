import java.util.Comparator;

public class DateCompare<Event> implements Comparator<Event> {

	@Override
	public int compare(Event e1, Event e2) {
		System.out.println("comparing dates... " + e1.getClass());
		/**
		 * 	Need to brush up on this interface...
		 * 	How to make it know that e1 and e2 are Event classes
		 * 	When running, it knows it's event classes
		 * 	But even with a cast in gives compile error
		 */
		return 0;
	}

}
