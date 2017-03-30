import java.util.ArrayList;
import java.util.Date;

public class Driver {

	public static void main(String[] args) {
		Calendar c = new Calendar();
		
		Event e = new Event("test", new Date(), new Date(), "description test");
		Event e2 = new Event("test2", new Date(), new Date(), "second test");
		c.addEvent(e);
		
		DateCompare dc = new DateCompare<Event>();
		dc.compare(e, e2);
		
		//System.out.println(c);
		
	}

}
