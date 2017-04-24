package model;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author MG Hirsch, edited by Robert Daro
 *
 */
public class Event implements Serializable {
	public String name;			// name of event
	public GregorianCalendar start;			// Start date and time
	public GregorianCalendar end;			// End date and time
	public String description;	// description of event
	public String category;		// category of event
	public String color;		// color of event
	public String location;		// location of event

	//This is most likely needed to make serialization work
	//private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param name			the name of this event
	 * @param start			the start date of this event
	 * @param end			the end date of this event
	 * @param description	the description of this event
	 */
	public Event(String name, GregorianCalendar start, GregorianCalendar end, String description) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.description = description;
	}
	
	/**
	 * 
	 * @param name			the name of this event
	 * @param start			the start date of this event
	 * @param end			the end date of this event
	 * @param description	the description of this event
	 * @param category		the category of this event
	 * @param color			the color of this event
	 * @param location		the location of this event
	 */
	public Event(String name, GregorianCalendar start, GregorianCalendar end, String description, String category,
				String color, String location) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.description = description;
		this.category = category;
		this.color = color;
		this.location = location;	
	}

	/**
	 * 
	 * @return	the name of this event
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name	the name of this event
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return	the start date of this event
	 */
	public GregorianCalendar getStart() {
		return start;
	}

	/**
	 * 
	 * @param start	the start date of this event
	 */
	public void setStart(GregorianCalendar start) {
		this.start = start;
	}

	/**
	 * 
	 * @return	the end date of this event
	 */
	public GregorianCalendar getEnd() {
		return end;
	}

	/**
	 * 
	 * @param end	the end date of this event
	 */
	public void setEnd(GregorianCalendar end) {
		this.end = end;
	}

	/**
	 * 
	 * @return	the description of this event
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description	the description of this event
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return the color of this event
	 */
	public String getColor() {
		return color;
	}

	/**
	 * 
	 * @param color the color of this event
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * 
	 * @return	the location of this event
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * 
	 * @param location	the location of this event
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * 
	 * @return	the category of this event
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 
	 * @param category	the category of this event
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 
	 */
	public String toString() {
		String result = "";
		result += "Name: " + name + "\n";
		result += "Start Date: " + start.get(GregorianCalendar.DAY_OF_MONTH) + "/" + start.get(GregorianCalendar.MONTH) + "/"+ start.get(GregorianCalendar.YEAR) + "\n";
		result += "Start Time: " + start.get(GregorianCalendar.HOUR_OF_DAY) + ":" + start.get(GregorianCalendar.MINUTE) + "\n";
		result += "End Date: " + end.get(GregorianCalendar.DAY_OF_MONTH) + "/" + end.get(GregorianCalendar.MONTH) + "/"+ end.get(GregorianCalendar.YEAR) + "\n";
		result += "End Time: " + end.get(GregorianCalendar.HOUR_OF_DAY) + ":" + end.get(GregorianCalendar.MINUTE) + "\n";
		result += "Description: " + description + "\n";
		return result;
	}
}