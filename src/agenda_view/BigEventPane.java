package agenda_view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;

import javafx.collections.ObservableList;
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
		TextField colorField = new TextField();
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
			String name = nameField.getText();
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
			
			Event event = new Event(nameField.getText(), start, end, descField.getText(), catField.getText(), colorField.getText(), locField.getText());
			agenda.getCalendar().addEvent(event);
			
			//exit the view
			MainWindow.loadWeekView();
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
		startPicker.setValue(LocalDate.of(e.getStart().get(GregorianCalendar.YEAR), 
										  e.getStart().get(GregorianCalendar.MONTH) + 1, 
										  e.getStart().get(GregorianCalendar.DATE))); 
		ComboBox<Integer> startFieldHour = new ComboBox<Integer>();
		for(int i  = 1; i <= 12; i++){
			startFieldHour.getItems().add((Integer) i);
		}
		startFieldHour.setValue(e.getStart().get(GregorianCalendar.HOUR));
		Label startColon = new Label(" : ");
		ComboBox<Integer> startFieldMinute = new ComboBox<Integer>();
		for(int i = 0; i < 60; i++){
			startFieldMinute.getItems().add((Integer) i);
		}
		startFieldMinute.setValue(e.getStart().get(GregorianCalendar.MINUTE));
		ComboBox<String> startCombo = new ComboBox<String>();
		startCombo.getItems().addAll(

				"AM",
				"PM");	
		if (e.getStart().get(GregorianCalendar.AM_PM) == GregorianCalendar.AM) {
			startCombo.setValue("AM");	
		} else {
			startCombo.setValue("PM");
		}
		
		
		
		DatePicker endPicker = new DatePicker();
		endPicker.setValue(LocalDate.of(e.getEnd().get(GregorianCalendar.YEAR), 
										  e.getEnd().get(GregorianCalendar.MONTH) + 1, 
										  e.getEnd().get(GregorianCalendar.DATE)));
		ComboBox<Integer> endFieldHour = new ComboBox<Integer>();
		for(int i  = 1; i <= 12; i++){
			endFieldHour.getItems().add((Integer) i);
		}
		endFieldHour.setValue(e.getEnd().get(GregorianCalendar.HOUR));
		Label endColon = new Label(" : ");
		ComboBox<Integer> endFieldMinute = new ComboBox<Integer>();
		for(int i = 0; i < 60; i++){
			endFieldMinute.getItems().add((Integer) i);
		}
		endFieldMinute.setValue(e.getEnd().get(GregorianCalendar.MINUTE));
		ComboBox<String> endCombo = new ComboBox<String>();
		endCombo.getItems().addAll(

				"AM",
				"PM");	
		if (e.getEnd().get(GregorianCalendar.AM_PM) == GregorianCalendar.AM) {
			endCombo.setValue("AM");	
		} else {
			endCombo.setValue("PM");
		}
		
		TextField locField = new TextField();
		locField.setText(e.getLocation());
		TextField colorField = new TextField();
		colorField.setText(e.getColor());
		TextField catField = new TextField();
		catField.setText(e.getCategory());

		nameBox.getChildren().add(nameField);
		descBox.getChildren().add(descField);
		startBox.getChildren().addAll(startPicker, startFieldHour, startColon, startFieldMinute,  startCombo);
		endBox.getChildren().addAll(endPicker, endFieldHour, endColon, endFieldMinute, endCombo);
		locBox.getChildren().add(locField);
		colorBox.getChildren().add(colorField);
		catBox.getChildren().add(catField);
		
		Button editButton = new Button("Edit");
		
		//TODO: add functionality to edit button
		
		this.getChildren().addAll(nameBox, descBox, startBox, endBox, locBox, colorBox, catBox, editButton);
	}
	
}
