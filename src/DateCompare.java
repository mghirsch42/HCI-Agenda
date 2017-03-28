import java.util.Comparator;

public class DateCompare<Event> implements Comparator<Event> {

	@Override
	public int compare(Event e1, Event e2) {
		System.out.println("comparing dates... " + e1.getClass());
		return 0;
	}

}
