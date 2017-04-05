import java.util.Comparator;

/**
 * @author MG Hirsch
 *
 */
public class DateCompare implements Comparator {
	
	@Override
	public int compare(Object e1, Object e2) {
		if(((Event)e1).getStart().before(((Event)e2).getStart())) {
			return 1;
		}
		return 0;
	}
}
