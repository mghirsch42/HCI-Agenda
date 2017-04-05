package model;
import java.util.Comparator;

/**
 * @author MG Hirsch
 *
 */
public class CategoryCompare implements Comparator {

	@Override
	public int compare(Object e1, Object e2) {
		if( ((Event)e1).getCategory().compareTo(((Event)e2).getCategory()) > 0  ) {
			return 0;
		}
		return 1;
	}
	
}
