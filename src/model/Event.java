package model;
import java.util.Date;

/**
 * @author MG Hirsch
 *
 */
public class Event {
	public String name;			// name of event
	public Date start;			// Start date and time
	public Date end;			// End date and time
	public String description;	// description of event
	public String category;		// category of event
	public String color;		// color of event
	public String location;		// location of event
	
	/**
	 * 
	 * @param name			the name of this event
	 * @param start			the start date of this event
	 * @param end			the end date of this event
	 * @param description	the description of this event
	 */
	public Event(String name, Date start, Date end, String description) {
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
	public Event(String name, Date start, Date end, String description, String category,
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
	public Date getStart() {
		return start;
	}

	/**
	 * 
	 * @param start	the start date of this event
	 */
	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 * 
	 * @return	the end date of this event
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * 
	 * @param end	the end date of this event
	 */
	public void setEnd(Date end) {
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
		result += "Start: " + start + "\n";
		result += "End: " + end + "\n";
		result += "Description: " + description + "\n";
		return result;
	}
}