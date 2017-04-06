package agenda_view;

import java.util.Date;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.Event;

public class BigEventPane extends VBox{
	private Event event;
	
	// for creating a new event
	public BigEventPane() {
		initEmpty();
	}
	
	public BigEventPane(Event e) {
		init(e);
		
	}
	
	private void initEmpty() {
		HBox nameBox = new HBox();
		HBox descBox = new HBox();
		HBox startBox = new HBox();
		HBox endBox = new HBox();
		HBox locBox = new HBox();
		HBox colorBox = new HBox();
		HBox catBox = new HBox();
		
		Label nameLbl = new Label("Name of Event");
		Label descLbl = new Label("Description");
		Label startLbl = new Label("Start Date");
		Label endLbl = new Label("End Date");
		Label locLbl = new Label("Location");
		Label colorLbl = new Label("Color");
		Label catLbl = new Label("Category");
		
		nameBox.getChildren().add(nameLbl);
		descBox.getChildren().add(descLbl);
		startBox.getChildren().add(startLbl);
		endBox.getChildren().add(endLbl);
		locBox.getChildren().add(locLbl);
		colorBox.getChildren().add(colorLbl);
		catBox.getChildren().add(catLbl);
		
		
		TextField nameField = new TextField();
		TextField descField = new TextField();
		DatePicker startPicker = new DatePicker();
		TextField startField = new TextField();
		ComboBox startCombo = new ComboBox();
		startCombo.getItems().addAll(
				"am",
				"pm");		
		DatePicker endPicker = new DatePicker();
		TextField endField = new TextField();
		ComboBox endCombo = new ComboBox();
		endCombo.getItems().addAll(
				"am",
				"pm");		
		TextField locField = new TextField();
		TextField colorField = new TextField();
		TextField catField = new TextField();
		
		nameBox.getChildren().add(nameField);
		descBox.getChildren().add(descField);
		startBox.getChildren().addAll(startPicker, startField, startCombo);
		endBox.getChildren().addAll(endPicker, endField, endCombo);
		locBox.getChildren().add(locField);
		colorBox.getChildren().add(colorField);
		catBox.getChildren().add(catField);
		
		Button submitBtn = new Button("Create Event");
		
		submitBtn.setOnAction((e) -> {
			/**
			 * TODO: 	Format dates from DatePicker, input field for time, and am/pm
			 * 			Create an event from all information
			 * 			Add event to MainWindow.calendar;
			 */
			
			
			// Date start = new Date();
			// Date end = new Date();
			// Event e = new Event(name, start, end, desc, cat, color, loc etc etc);
			// MainWindow.calendar.addEvent(e);
			
		});
		
		
		this.getChildren().addAll(nameBox, descBox, startBox, endBox, locBox, colorBox, catBox, submitBtn);
		
		
	}
	
	private void init(Event e) {
		
	}
	
}
