package agenda_view;

import java.time.LocalDate;



import java.util.GregorianCalendar;


import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Agenda;
import model.Event;

public class BigEventPane extends VBox{
	private Event event;
	private Agenda agenda;
	
	
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
		
		
		HBox nameBox = new HBox();
		HBox descBox = new HBox();
		HBox startBox = new HBox();
		HBox endBox = new HBox();
		HBox locBox = new HBox();
		HBox colorBox = new HBox();
		HBox catBox = new HBox();
		
		Label nameLbl = new Label("Name of Event ");
		Label descLbl = new Label("Description ");
		Label startLbl = new Label("Start Date ");
		Label endLbl = new Label("End Date ");
		Label locLbl = new Label("Location ");
		Label colorLbl = new Label("Color ");
		Label catLbl = new Label("Category ");
		
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
		startPicker.setEditable(false);
		startPicker.setValue(LocalDate.now());
		ComboBox<Integer> startFieldHour = new ComboBox<Integer>();
		for(int i  = 1; i <= 12; i++){
			startFieldHour.getItems().add((Integer) i);
		}
		startFieldHour.setValue(12);
		Label startColon = new Label(" : ");
		ComboBox<Integer> startFieldMinute = new ComboBox<Integer>();
		for(int i = 0; i < 60; i++){
			startFieldMinute.getItems().add((Integer) i);
		}
		startFieldMinute.setValue(0);
		ComboBox<String> startCombo = new ComboBox<String>();
		startCombo.getItems().addAll(

				"AM",
				"PM");		

		startCombo.setValue("AM");

		DatePicker endPicker = new DatePicker();
		endPicker.setValue(LocalDate.now());
		endPicker.setEditable(false);
		ComboBox<Integer> endFieldHour = new ComboBox<Integer>();
		for(int i  = 1; i <= 12; i++){
			endFieldHour.getItems().add((Integer) i);
		}
		endFieldHour.setValue(12);
		Label endColon = new Label(" : ");
		ComboBox<Integer> endFieldMinute = new ComboBox<Integer>();
		for(int i = 0; i < 60; i++){
			endFieldMinute.getItems().add((Integer) i);
		}
		endFieldMinute.setValue(0);
		ComboBox<String> endCombo = new ComboBox<String>();
		endCombo.getItems().addAll(
				"AM",
				"PM");		

		endCombo.setValue("PM");
		
		TextField locField = new TextField();
		ComboBox<String> colorField = new ComboBox<String>();
		colorField.getItems().addAll("Blue", "Red", "Green", "Yellow", "Purple", "Orange"); 
		colorField.setValue("Blue");
		TextField catField = new TextField();
		
		nameBox.getChildren().add(nameField);
		descBox.getChildren().add(descField);
		startBox.getChildren().addAll(startPicker, startFieldHour, startColon, startFieldMinute,  startCombo);
		endBox.getChildren().addAll(endPicker, endFieldHour, endColon, endFieldMinute, endCombo);
		locBox.getChildren().add(locField);
		colorBox.getChildren().add(colorField);
		catBox.getChildren().add(catField);
		
		Button submitBtn = new Button("Create Event");
		
		submitBtn.setOnAction((e) -> {
			LocalDate startDate = startPicker.getValue();
			LocalDate endDate = endPicker.getValue();
			int startTimeHour = startFieldHour.getValue();
			if (startCombo.getValue().equalsIgnoreCase("pm")) startTimeHour = startTimeHour + 12;
			int startTimeMinute = startFieldMinute.getValue();
			int endTimeHour = endFieldHour.getValue();
			if (endCombo.getValue().equalsIgnoreCase("pm")) endTimeHour = endTimeHour + 12;
			int endTimeMinute = endFieldMinute.getValue();
			
			GregorianCalendar start = new GregorianCalendar(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth(), startTimeHour, startTimeMinute);
			GregorianCalendar end = new GregorianCalendar(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfMonth(), endTimeHour, endTimeMinute);
			
			Event event = new Event(nameField.getText(), start, end, descField.getText(), catField.getText(), colorField.getValue(), locField.getText());

			agenda.getCalendar().addEvent(event);
			
			//exit the view
			MainWindow.loadWeekView();
		});
		
		this.getChildren().addAll(nameBox, descBox, startBox, endBox, locBox, colorBox, catBox, submitBtn);
	}
	
	// initialize a form with event data
	private void init(Event event) {
		HBox nameBox = new HBox();
		HBox descBox = new HBox();
		HBox startBox = new HBox();
		HBox endBox = new HBox();
		HBox locBox = new HBox();
		HBox colorBox = new HBox();
		HBox catBox = new HBox();
		
		Label nameLbl = new Label("Name of Event ");
		Label descLbl = new Label("Description ");
		Label startLbl = new Label("Start Date ");
		Label endLbl = new Label("End Date ");
		Label locLbl = new Label("Location ");
		Label colorLbl = new Label("Color ");
		Label catLbl = new Label("Category ");
		
		nameBox.getChildren().add(nameLbl);
		descBox.getChildren().add(descLbl);
		startBox.getChildren().add(startLbl);
		endBox.getChildren().add(endLbl);
		locBox.getChildren().add(locLbl);
		colorBox.getChildren().add(colorLbl);
		catBox.getChildren().add(catLbl);
		
		
		TextField nameField = new TextField();
		nameField.setText(event.getName());
		TextField descField = new TextField();
		descField.setText(event.getDescription());
		DatePicker startPicker = new DatePicker();

		startPicker.setValue(LocalDate.of(event.getStart().get(GregorianCalendar.YEAR), 
										  event.getStart().get(GregorianCalendar.MONTH) + 1, 
										  event.getStart().get(GregorianCalendar.DATE))); 
		ComboBox<Integer> startFieldHour = new ComboBox<Integer>();
		for(int i  = 1; i <= 12; i++){
			startFieldHour.getItems().add((Integer) i);
		}
		startFieldHour.setValue(event.getStart().get(GregorianCalendar.HOUR));
		Label startColon = new Label(" : ");
		ComboBox<Integer> startFieldMinute = new ComboBox<Integer>();
		for(int i = 0; i < 60; i++){
			startFieldMinute.getItems().add((Integer) i);
		}
		startFieldMinute.setValue(event.getStart().get(GregorianCalendar.MINUTE));
		ComboBox<String> startCombo = new ComboBox<String>();

		startCombo.getItems().addAll(
				"AM",
				"PM");	
		if (event.getStart().get(GregorianCalendar.AM_PM) == GregorianCalendar.AM) {
			startCombo.setValue("AM");	
		} else {
			startCombo.setValue("PM");
		}
		
		
		
		DatePicker endPicker = new DatePicker();
		endPicker.setValue(LocalDate.of(event.getEnd().get(GregorianCalendar.YEAR), 
										  event.getEnd().get(GregorianCalendar.MONTH) + 1, 
										  event.getEnd().get(GregorianCalendar.DATE)));
		ComboBox<Integer> endFieldHour = new ComboBox<Integer>();
		for(int i  = 1; i <= 12; i++){
			endFieldHour.getItems().add((Integer) i);
		}
		endFieldHour.setValue(event.getEnd().get(GregorianCalendar.HOUR));
		Label endColon = new Label(" : ");
		ComboBox<Integer> endFieldMinute = new ComboBox<Integer>();
		for(int i = 0; i < 60; i++){
			endFieldMinute.getItems().add((Integer) i);
		}
		endFieldMinute.setValue(event.getEnd().get(GregorianCalendar.MINUTE));
		ComboBox<String> endCombo = new ComboBox<String>();

		endCombo.getItems().addAll(

				"AM",
				"PM");	
		if (event.getEnd().get(GregorianCalendar.AM_PM) == GregorianCalendar.AM) {
			endCombo.setValue("AM");	
		} else {
			endCombo.setValue("PM");
		}
		
		TextField locField = new TextField();
		locField.setText(event.getLocation());
		ComboBox<String> colorField = new ComboBox<String>();
		colorField.getItems().addAll("Blue", "Red", "Green", "Yellow", "Purple", "Orange");
		colorField.setValue(event.getColor());
		TextField catField = new TextField();

		catField.setText(event.getCategory());
		

		nameBox.getChildren().add(nameField);
		descBox.getChildren().add(descField);
		startBox.getChildren().addAll(startPicker, startFieldHour, startColon, startFieldMinute,  startCombo);
		endBox.getChildren().addAll(endPicker, endFieldHour, endColon, endFieldMinute, endCombo);
		locBox.getChildren().add(locField);
		colorBox.getChildren().add(colorField);
		catBox.getChildren().add(catField);
		
		Button editButton = new Button("Edit");
		
		//TODO: add functionality to edit button
		
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
					event.getStart().get(GregorianCalendar.HOUR),		// hour
					event.getStart().get(GregorianCalendar.MINUTE));		// minute
			event.setStart(start);
			
			date = endPicker.getValue().toString();
			tokens = date.toString().split("-");

			GregorianCalendar end = new GregorianCalendar(
					Integer.parseInt(tokens[0]), // year
					Integer.parseInt(tokens[1]) -1, 	// month
					Integer.parseInt(tokens[2]),		// day
					event.getEnd().get(GregorianCalendar.HOUR),			// hour
					event.getEnd().get(GregorianCalendar.MINUTE));			// minute
			event.setEnd(end);
			event.setColor(colorField.getValue());
			event.setCategory(catField.getText());
			event.setLocation(locField.getText());
			
			
			//exit the view
			MainWindow.loadWeekView();
		});
		
		this.getChildren().addAll(nameBox, descBox, startBox, endBox, locBox, colorBox, catBox, editButton);
	}
	
}
