package agenda_view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Agenda;
import model.Event;

public class MonthEventPane extends VBox {
	private Event event;
	private Agenda agenda;
	
	public MonthEventPane(Event event, Agenda a) {
		this.event = event;
		this.agenda = a;
		
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
