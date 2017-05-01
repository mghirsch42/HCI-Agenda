package agenda_view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Agenda;
import model.Event;

public class MonthEventPane extends VBox {
	private Event event;
	private Agenda agenda;
	
	public MonthEventPane(BigEventPane bigEvent, Event event, final Agenda a) {
		this.event = event;
		this.agenda = a;
		
		Button title = new Button(event.getName());
		title.setStyle("-fx-background-color: " +  event.getColor().toLowerCase() +";");
		title.setOnAction((e) -> {
			bigEvent.selectEvent(event);
			MainWindow.getRoot().setCenter(bigEvent);
		});
		
		this.getChildren().add(title);
		
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
