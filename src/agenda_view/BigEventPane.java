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
				"am",
				"pm");		
		startCombo.setValue("am");
		
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
				"am",
				"pm");	
		endCombo.setValue("pm");
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
		
		submitBtn.setOnAction(e -> {
		
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
		MainWindow.agenda.getCalendar().addEvent(event);
		
		//exit the view
		MainWindow.loadWeekView();
		});
		
		this.getChildren().addAll(nameBox, descBox, startBox, endBox, locBox, colorBox, catBox, submitBtn);
		
		
	}
	
	private void init(Event e) {
		
	}
	
}
