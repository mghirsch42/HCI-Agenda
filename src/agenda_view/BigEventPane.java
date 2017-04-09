package agenda_view;

import java.time.LocalDate;
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
				"AM",
				"PM");		
		DatePicker endPicker = new DatePicker();
		TextField endField = new TextField();
		ComboBox endCombo = new ComboBox();
		endCombo.getItems().addAll(
				"AM",
				"PM");		
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
			
			String date = startPicker.getValue().toString();
			
			long startDate = (long) (Long.parseLong(date.substring(0, 1)) * (2.628 * Math.pow(10, 9)) + 
							 Long.parseLong(date.substring(3, 4)) * (8.46 * Math.pow(10, 7)) +
							 (Long.parseLong(date.substring(6)) - 1970) * (3.154 * Math.pow(10,  10)));
			
			date = endPicker.getValue().toString();
			long endDate = (long) (Long.parseLong(date.substring(0, 1)) * (2.628 * Math.pow(10, 9)) + 
					 Long.parseLong(date.substring(3, 4)) * (8.46 * Math.pow(10, 7)) +
					 (Long.parseLong(date.substring(6)) - 1970) * (3.154 * Math.pow(10,  10)));
			
			// DatePicker gives us mm/dd/yyyy
			// Date(long date) param is milliseconds since Jan 1 1970, 00:00:00
			
			
			Date start = new Date(startDate);
			Date end = new Date(endDate);
			Event event = new Event(nameField.getText(), start, end, descField.getText(), catField.getText(), 
					colorField.getText(), locField.getText());
			MainWindow.calendar.addEvent(event);
			
		});
		
		
		this.getChildren().addAll(nameBox, descBox, startBox, endBox, locBox, colorBox, catBox, submitBtn);
		
		
	}
	
	private void init(Event e) {
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
		nameField.setText(e.getName());
		TextField descField = new TextField();
		descField.setText(e.getDescription());
		DatePicker startPicker = new DatePicker();
		startPicker.setValue(LocalDate.of(Integer.parseInt(e.getStart().toString().substring(6, 9)), 
										  Integer.parseInt(e.getStart().toString().substring(0, 1)), 
										  Integer.parseInt(e.getStart().toString().substring(3, 4)))); 
		TextField startField = new TextField();
		startField.setText("" + e.getStart().getHours() + ":" + e.getStart().getMinutes());
		ComboBox startCombo = new ComboBox();
		startCombo.getItems().addAll(
				"AM",
				"PM");		
		DatePicker endPicker = new DatePicker();
		endPicker.setValue(LocalDate.of(Integer.parseInt(e.getEnd().toString().substring(6, 9)), 
										  Integer.parseInt(e.getEnd().toString().substring(0, 1)), 
										  Integer.parseInt(e.getEnd().toString().substring(3, 4)))); 
		TextField endField = new TextField();
		endField.setText("" + e.getEnd().getHours() + ":" + e.getEnd().getMinutes());
		ComboBox endCombo = new ComboBox();
		endCombo.getItems().addAll(
				"AM",
				"PM");		
		TextField locField = new TextField();
		locField.setText(e.getLocation());
		TextField colorField = new TextField();
		colorField.setText(e.getColor());
		TextField catField = new TextField();
		catField.setText(e.getCategory());
		
		nameBox.getChildren().add(nameField);
		descBox.getChildren().add(descField);
		startBox.getChildren().addAll(startPicker, startField, startCombo);
		endBox.getChildren().addAll(endPicker, endField, endCombo);
		locBox.getChildren().add(locField);
		colorBox.getChildren().add(colorField);
		catBox.getChildren().add(catField);
	}
	
}
