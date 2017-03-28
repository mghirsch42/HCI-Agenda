import java.util.ArrayList;
import java.util.Date;

public class Driver {

	public static void main(String[] args) {
		Calendar c = new Calendar();
		
		Event e = new Event("test", new Date(), new Date(), "description test");
		
		c.addEvent(e);
		
		System.out.println(c);
		
	}

}
