import java.util.Date;

public class Event {
	public String name;
	public Date start;			// Start date and time
	public Date end;			// End date and time
	public String description;
	public String category;
	public String color;
	public String location;
	
	public Event(String name, Date start, Date end, String description) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.description = description;
	}
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String toString() {
		String result = "";
		result += "Name: " + name + "\n";
		result += "Start: " + start + "\n";
		result += "End: " + end + "\n";
		result += "Description: " + description + "\n";
		return result;
	}
}