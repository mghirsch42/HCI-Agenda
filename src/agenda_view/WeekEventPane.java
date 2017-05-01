package agenda_view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Agenda;
import model.Event;

public class WeekEventPane extends VBox{
	public Event event;
	private Agenda agenda;
	
	public WeekEventPane(Event event, Agenda a) {
		this.event = event;
		this.agenda = a;
		Button title = new Button(event.getName());
		
		title.setOnAction((e) -> {
			BigEventPane pane = new BigEventPane(event, agenda);
			MainWindow.getRoot().setCenter(pane);
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
