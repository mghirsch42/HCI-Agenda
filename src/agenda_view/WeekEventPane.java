package agenda_view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Event;

public class WeekEventPane extends VBox{
	public Event event;
	
	public WeekEventPane(Event e) {
		event = e;
		
		Label title = new Label(e.getName());
		
		this.getChildren().add(title);
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
