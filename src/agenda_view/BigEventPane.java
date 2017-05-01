package agenda_view;

import java.time.LocalDate;
import java.util.GregorianCalendar;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Agenda;
import model.Event;

public class BigEventPane extends BorderPane{
	private Event event;
	private Agenda agenda;
	
	private VBox centerBox = new VBox(5);
	
	private HBox nameBox = new HBox(5);
	private HBox descBox = new HBox(5);
	private HBox startBox = new HBox(5);
	private HBox endBox = new HBox(5);
	private HBox locBox = new HBox(5);
	private HBox colorBox = new HBox(5);
	private HBox catBox = new HBox(5);
	
	private Label nameLbl = new Label("Name of Event");
	private Label descLbl = new Label("Description");
	private Label startLbl = new Label("Start Date");
	private Label endLbl = new Label("End Date");
	private Label locLbl = new Label("Location");
	private Label colorLbl = new Label("Color");
	private Label catLbl = new Label("Category");
	
	private TextField nameField = new TextField();
	private TextField descField = new TextField();
	
	private DatePicker startPicker = new DatePicker();
	//private TextField startField = new TextField();
	private ComboBox<Integer> startHourCombo = new ComboBox<Integer>();
	private Label startColon = new Label(":");
	private ComboBox<String> startCombo = new ComboBox<String>();
	
	private DatePicker endPicker = new DatePicker();
	private ComboBox<Integer> endHourCombo = new ComboBox<Integer>();
	private Label endColon = new Label(":");
	private ComboBox<Integer> endMinuteCombo = new ComboBox<Integer>();
	private ComboBox<String> endCombo = new ComboBox<String>();
	
	private TextField locField = new TextField();
	private TextField colorField = new TextField();
	private TextField catField = new TextField();
	
	private Button submitBtn = new Button("Create Event");
	private Button editButton = new Button("Edit");
	
	// for creating a new event
	public BigEventPane(final Agenda a) {
		initEmpty();
		this.agenda = a;
	}
	
	// for displaying/editing an event
	public BigEventPane(Event e, final Agenda a) {
		init(e);
		this.agenda = a;
	}
	
	// initialize an empty form
	private void initEmpty() {
		
		
		startCombo.getItems().addAll(
				"AM",
				"PM");		
		
		endCombo.getItems().addAll(
				"AM",
				"PM");		
				
		//add the hours to the hour combo boxes
		for (int i = 1; i <= 12; i++){
			startHourCombo.getItems().add(i);
			endHourCombo.getItems().add(i);
		}
		//add the minutes to the minute combo boxes
		for (int i = 0; i <= 60; i++){
			startMinuteCombo.getItems().add(i);
			endMinuteCombo.getItems().add(i);
		}
		//give all the combo boxes default values
		startCombo.setValue("PM");
		endCombo.setValue("PM");
		startHourCombo.setValue(12);
		endHourCombo.setValue(1);
		startMinuteCombo.setValue(0);
		endMinuteCombo.setValue(0);
		
		//Aligning the test fields 
		nameLbl.setPadding(new Insets(0, 1, 0, 0));
		descLbl.setPadding(new Insets(0, 19, 0, 0));
		startLbl.setPadding(new Insets(0, 27, 0, 0));
		endLbl.setPadding(new Insets(0, 31, 0, 0));
		locLbl.setPadding(new Insets(0, 34, 0, 0));
		colorLbl.setPadding(new Insets(0, 51, 0, 0));
		catLbl.setPadding(new Insets(0, 32, 0, 0));
		
		//Padding around boxes
		nameBox.setPadding(new Insets(10, 0, 5, 5));
		descBox.setPadding(new Insets(0, 0, 5, 5));
		startBox.setPadding(new Insets(0, 0, 5, 5));
		endBox.setPadding(new Insets(0, 0, 5, 5));
		locBox.setPadding(new Insets(0, 0, 5, 5));
		colorBox.setPadding(new Insets(0, 0, 5, 5));
		catBox.setPadding(new Insets(0, 0, 5, 5));
		
		
		
		
		nameBox.getChildren().addAll(nameLbl, nameField);
		descBox.getChildren().addAll(descLbl, descField);
		startBox.getChildren().addAll(startLbl, startPicker, startHourCombo, startColon, startMinuteCombo, startCombo);
		endBox.getChildren().addAll(endLbl, endPicker, endHourCombo, endColon, endMinuteCombo, endCombo);
		locBox.getChildren().addAll(locLbl, locField);
		colorBox.getChildren().addAll(colorLbl, colorField);
		catBox.getChildren().addAll(catLbl, catField);
		
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
			
			
			GregorianCalendar start = new GregorianCalendar(
					Integer.parseInt(tokens[0]),	// year
					Integer.parseInt(tokens[1]) -1, 	// month - not sure why, maybe indexes months from 0?
					Integer.parseInt(tokens[2]),	// day
					startHourCombo.getValue(),		// hour
					startMinuteCombo.getValue());		// minute
			
			////////////////////////
			// Determine end dates
			////////////////////////
			
			date = endPicker.getValue().toString();
			tokens = date.toString().split("-");
			
			GregorianCalendar end = new GregorianCalendar(
					Integer.parseInt(tokens[0]), // year
					Integer.parseInt(tokens[1]) -1, 	// month
					Integer.parseInt(tokens[2]),		// day
					endHourCombo.getValue(),			// hour
					endMinuteCombo.getValue());			// minute
			
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
		
		centerBox.getChildren().addAll(nameBox, descBox, startBox, endBox, locBox, colorBox, catBox, submitBtn);
		this.setCenter(centerBox);
	}
	
	// initialize a form with event data
	private void init(Event event) {
		
		//add the hours to the hour combo boxes
		for (int i = 1; i <= 12; i++){
			startHourCombo.getItems().add(i);
			endHourCombo.getItems().add(i);
		}
		//add the minutes to the minute combo boxes
		for (int i = 0; i <= 60; i++){
			startMinuteCombo.getItems().add(i);
			endMinuteCombo.getItems().add(i);
		}
				
		nameField.setText(event.getName());
		descField.setText(event.getDescription());
		startPicker.setValue(LocalDate.of(event.getStart().get(GregorianCalendar.YEAR), 
										  event.getStart().get(GregorianCalendar.MONTH)+1, 
										  event.getStart().get(GregorianCalendar.DATE))); 
		
		//startField.setText("" + event.getStart().get(GregorianCalendar.HOUR) + ":" + event.getStart().get(GregorianCalendar.MINUTE));
		startHourCombo.setValue(event.getStart().get(GregorianCalendar.HOUR) + 1);
		startMinuteCombo.setValue(event.getStart().get(GregorianCalendar.MINUTE));
		startCombo.getItems().addAll(
				"AM",
				"PM");		
		//check if start time is am or pm
		if (event.getStart().get(GregorianCalendar.AM_PM) == GregorianCalendar.AM){
			startCombo.setValue("AM");
		} else {
			startCombo.setValue("PM");
		}
		
		endPicker.setValue(LocalDate.of(event.getEnd().get(GregorianCalendar.YEAR), 
										event.getEnd().get(GregorianCalendar.MONTH)+1, 
										event.getEnd().get(GregorianCalendar.DATE))); 
		
		//endField.setText("" + event.getEnd().get(GregorianCalendar.HOUR) + ":" + event.getEnd().get(GregorianCalendar.MINUTE));
		endHourCombo.setValue(event.getEnd().get(GregorianCalendar.HOUR) + 1);
		endMinuteCombo.setValue(event.getEnd().get(GregorianCalendar.MINUTE));
		endCombo.getItems().addAll(
				"AM",
				"PM");		
		//check if end time is am or pm
		if (event.getEnd().get(GregorianCalendar.AM_PM) == GregorianCalendar.AM){
			endCombo.setValue("AM");
		} else {
			endCombo.setValue("PM");
		}
		
		locField.setText(event.getLocation());
		colorField.setText(event.getColor());
		catField.setText(event.getCategory());
		
		
		//Aligning the test fields 
		nameLbl.setPadding(new Insets(0, 1, 0, 0));
		descLbl.setPadding(new Insets(0, 19, 0, 0));
		startLbl.setPadding(new Insets(0, 27, 0, 0));
		endLbl.setPadding(new Insets(0, 31, 0, 0));
		locLbl.setPadding(new Insets(0, 34, 0, 0));
		colorLbl.setPadding(new Insets(0, 51, 0, 0));
		catLbl.setPadding(new Insets(0, 32, 0, 0));
		
		//Padding around boxes
		nameBox.setPadding(new Insets(10, 0, 5, 5));
		descBox.setPadding(new Insets(0, 0, 5, 5));
		startBox.setPadding(new Insets(0, 0, 5, 5));
		endBox.setPadding(new Insets(0, 0, 5, 5));
		locBox.setPadding(new Insets(0, 0, 5, 5));
		colorBox.setPadding(new Insets(0, 0, 5, 5));
		catBox.setPadding(new Insets(0, 0, 5, 5));
		
		nameBox.getChildren().addAll(nameLbl, nameField);
		descBox.getChildren().addAll(descLbl, descField);
		startBox.getChildren().addAll(startLbl, startPicker, startHourCombo, startColon, startMinuteCombo, startCombo);
		endBox.getChildren().addAll(endLbl, endPicker, endHourCombo, endColon, endMinuteCombo, endCombo);
		locBox.getChildren().addAll(locLbl, locField);
		colorBox.getChildren().addAll(colorLbl, colorField);
		catBox.getChildren().addAll(catLbl, catField);
				
		editButton.setOnAction((e) -> {
			event.setName(nameField.getText());
			event.setDescription(descField.getText());
			
			String date = startPicker.getValue().toString();
			
			// tokens will look like yyyy-mm-dd
			String[] tokens = date.toString().split("-");
		
			GregorianCalendar start = new GregorianCalendar(
					Integer.parseInt(tokens[0]),	// year - date takes years since 1900
					Integer.parseInt(tokens[1]) -1, 	// month - not sure why, maybe indexes months from 0?
					Integer.parseInt(tokens[2]),	// day
					startHourCombo.getValue(),		// hour
					startMinuteCombo.getValue());		// minute
			event.setStart(start);
			
			date = endPicker.getValue().toString();
			tokens = date.toString().split("-");
			
			GregorianCalendar end = new GregorianCalendar(
					Integer.parseInt(tokens[0]), // year
					Integer.parseInt(tokens[1]) -1, 	// month
					Integer.parseInt(tokens[2]),		// day
					endHourCombo.getValue(),			// hour
					endMinuteCombo.getValue());			// minute
			event.setEnd(end);
			event.setColor(colorField.getText());
			event.setCategory(catField.getText());
			event.setLocation(locField.getText());
		});
		
				
		centerBox.getChildren().addAll(nameBox, descBox, startBox, endBox, locBox, colorBox, catBox, editButton);
		this.setCenter(centerBox);
	}
	
}
