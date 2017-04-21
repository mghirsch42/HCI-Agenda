package agenda_view;

import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;

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
import model.Agenda;
import model.Event;

public class BigEventPane extends VBox{
	private Event event;
	private Agenda agenda;
	
	// for creating a new event
	public BigEventPane(Agenda a) {
		initEmpty();
		this.agenda = a;
	}
	
	// for displaying/editing an event
	public BigEventPane(Event e, Agenda a) {
		init(e);
		this.agenda = a;
	}
	
	// initialize an empty form
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
			
			/*
			 * Parse the fields and create appropriate event objects
			 */
			
			/////////////////////////
			// Determine start dates
			/////////////////////////
			
			String date = startPicker.getValue().toString();
			
			System.out.println("Here is your date string: " +
								date);
			
			// tokens will look like yyyy-mm-dd
			String[] tokens = date.toString().split("-");
			
			String[] time = startField.getText().split(":");
			
			@SuppressWarnings("deprecation")
			GregorianCalendar start = new GregorianCalendar(
					Integer.parseInt(tokens[0]) - 1900,	// year - date takes years since 1900
					Integer.parseInt(tokens[1]) -1, 	// month - not sure why, maybe indexes months from 0?
					Integer.parseInt(tokens[2]),	// day
					Integer.parseInt(time[0]),		// hour
					Integer.parseInt(time[1]));		// minute
			
			////////////////////////
			// Determine end dates
			////////////////////////
			
			date = endPicker.getValue().toString();
			tokens = date.toString().split("-");
			time = endField.getText().split(":");
			
			@SuppressWarnings("deprecation")
			GregorianCalendar end = new GregorianCalendar(
					Integer.parseInt(tokens[0]) - 1900, // year
					Integer.parseInt(tokens[1]) -1, 	// month
					Integer.parseInt(tokens[2]),		// day
					Integer.parseInt(time[0]),			// hour
					Integer.parseInt(time[1]));			// minute
			
			/////////////////////
			// Create and add the event
			/////////////////////
			
			Event event = new Event(nameField.getText(), start, end, descField.getText(), catField.getText(), 
					colorField.getText(), locField.getText());
			
			
						
			agenda.getCalendar().addEvent(event);
			
			//Testing output
			//TODO: Remove when testing is complete.
			System.out.println("Agenda After Adding Event");
			System.out.println(agenda);
			
		});
		
		
		this.getChildren().addAll(nameBox, descBox, startBox, endBox, locBox, colorBox, catBox, submitBtn);
	}
	
	// initialize a form with event data
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
		
		startPicker.setValue(LocalDate.of(e.getStart().get(GregorianCalendar.YEAR)+1900, 
										  e.getStart().get(GregorianCalendar.MONTH)+1, 
										  e.getStart().get(GregorianCalendar.DATE))); 
		TextField startField = new TextField();
		startField.setText("" + e.getStart().get(GregorianCalendar.HOUR) + ":" + e.getStart().get(GregorianCalendar.MINUTE));
		ComboBox startCombo = new ComboBox();
		startCombo.getItems().addAll(
				"AM",
				"PM");		
		DatePicker endPicker = new DatePicker();
		endPicker.setValue(LocalDate.of(e.getEnd().get(GregorianCalendar.YEAR)+1900, 
										e.getEnd().get(GregorianCalendar.MONTH)+1, 
										e.getEnd().get(GregorianCalendar.DATE))); 
		TextField endField = new TextField();
		endField.setText("" + e.getEnd().get(GregorianCalendar.HOUR) + ":" + e.getEnd().get(GregorianCalendar.MINUTE));
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
		
		Button editButton = new Button("Edit");
		
		//TODO: add functionality to edit button
		
		this.getChildren().addAll(nameBox, descBox, startBox, endBox, locBox, colorBox, catBox, editButton);
	}
	
}
