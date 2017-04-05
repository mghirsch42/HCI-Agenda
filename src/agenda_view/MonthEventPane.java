package agenda_view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Event;

public class MonthEventPane extends VBox {
	private Event event;
	
	public MonthEventPane(Event e) {
		event = e;
		
		Label title = new Label(event.getName());
		this.getChildren().add(title);
		
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
